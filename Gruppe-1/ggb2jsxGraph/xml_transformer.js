const geogebraBoilerplate = `<?xml version="1.0" encoding="utf-8"?>
<geogebra>
<euclidianView>
	<coordSystem xZero="215.0" yZero="315.0" scale="50.0" yscale="50.0"/>
	<evSettings axes="true" grid="true" gridIsBold="false" pointCapturing="3" rightAngleStyle="1" checkboxSize="26" gridType="3"/>
</euclidianView>
<kernel>
	<decimals val="2"/>
	<angleUnit val="degree"/>
</kernel>
<construction>
</construction>
</geogebra>`;

const GGB_HIGHLIGHT_RED = '<objColor r="255" g="0" b="0" alpha="0.0"/>';
const GGB_COORDS_SLIDER = '<coords x="1" y="1" z="1"/>';

// to keep track of ids of construction tools used by macros
// so we don't use them multiple times
let macroIdRepository = {};

let propExplanations = [];

// construction tools that are entirely equivalent between Geoproof/GeoGebra, including parameters and their order
const commandNameMapping = {
    'p_bisector': 'LineBisector',
    'intersection_point': 'Intersect',
    'midpoint': 'Midpoint',
    'pp_line': 'Line',
    'ortho_line': 'OrthogonalLine',
    'par_line': 'Line',
    'pc_circle': 'Circle',
    'p3_circle': 'Circle'
};
// any construction tools not covered by the above
// callback arguments are always (...inputs, output)
const commandMacroMapping = {
    'altitude': (pointTop, pointBase1, pointBase2, finalOutput) => {
        const lineId = `base of ${finalOutput}`;
        const returnStr =
            createCommandXml('Line', [pointBase1, pointBase2], lineId, 'line') +
            createCommandXml('OrthogonalLine', [pointTop, lineId], finalOutput, 'line');
        markIdUsed(lineId);
        return returnStr;
    },
    'pedalpoint': (point, line, finalOutput) => {
        const orthogonalLineId = `ortho(${point}, ${line})`;
        const returnStr =
            createCommandXml('OrthogonalLine', [point, line], orthogonalLineId, 'line') + 
            createCommandXml('Intersect', [line, orthogonalLineId], finalOutput, 'point');
        markIdUsed(orthogonalLineId);
        return returnStr;
    },
    'median': (pointTop, pointBase1, pointBase2, finalOutput) => {
        const midpointId = `mid(${pointBase1}, ${pointBase2})`;
        const returnStr =
            createCommandXml('Midpoint', [pointBase1, pointBase2], midpointId, 'point') +
            createCommandXml('Line', [pointTop, midpointId], finalOutput, 'line');
        markIdUsed(midpointId);
        return returnStr;
    },
    'line_slider': (line, finalOutput) => {
        return createCommandXml('Point', [line], finalOutput, 'point', [GGB_COORDS_SLIDER]);
    }
};

const propMacroMapping = {
    'eq_dist': (A, B, C, D) => {
        const firstLineId = `dist(${A}, ${B})`;
        const secondLineId = `dist(${C}, ${D})`;
        const returnStr =
            createCommandXml('Segment', [A, B], firstLineId, 'line', [GGB_HIGHLIGHT_RED]) +
            createCommandXml('Segment', [C, D], secondLineId, 'line', [GGB_HIGHLIGHT_RED]);
        markIdUsed(firstLineId, secondLineId);
        propExplanations.push(`Distance (${A}, ${B}) is equal to distance (${C}, ${D})`)
        return returnStr;
    },
    'eq_angle': (A, B, C, D, E, F) => {
        const firstAngleId = `ang(${A}, ${B}, ${C})`;
        const secondAngleId = `ang(${D}, ${E}, ${F})`;
        const returnStr =
            createCommandXml('Angle', [A, B, C], firstAngleId, 'angle', [GGB_HIGHLIGHT_RED]) +
            createCommandXml('Angle', [D, E, F], secondAngleId, 'angle', [GGB_HIGHLIGHT_RED]);
        markIdUsed(firstAngleId, secondAngleId);
        propExplanations.push(`Angle (${A}, ${B}, ${C}) is equal to angle (${D}, ${E}, ${F})`)
        return returnStr;
    },
    'is_concurrent': (...lines) => {
        const intersectionId = `inter(${lines.join(', ')})`;
        const returnStr = createCommandXml('Intersect', lines, intersectionId, 'point', [GGB_HIGHLIGHT_RED]);
        markIdUsed(intersectionId);
        propExplanations.push(`Lines ${lines.join(', ')} are concurrent`);
        return returnStr;
    }
};

function markIdUsed(...ids) {
    for (const id of ids) macroIdRepository[id] = true;
}

function transformXml(inputXml) {
    const $inputXml = $($.parseXML(inputXml));
    const $resultXml = $($.parseXML(geogebraBoilerplate));
    const $construction = $resultXml.find('construction');

    const $freePoints = $inputXml.find('Points Point');
    const freePointGenerator = createCoordGenerator();
    $freePoints.each((i, elem) => {
        $construction.append(transformFreePointXml(elem, freePointGenerator));
    });

    const $assignments = $inputXml.find('Assignments').children();
    $assignments.each((i, elem) => {
        $construction.append(transformCommandXml(elem));
    });

    const $props = $inputXml.find('Conclusions prop');
    $props.each((i, elem) => {
        $construction.append(transformPropXml(elem));
    });

    return new XMLSerializer().serializeToString($resultXml[0]);
}

function* createCoordGenerator() {
    yield [0, 1];
    yield [1, 0];
    yield [2, 1.5];
    yield [0.7, 3];
    yield [3, 2];
}

function createCommandXml(commandName, inputIds, outputId, outputType, additionalElementData) {
    // check if outputId has been used already, so we don't get duplicates
    if (macroIdRepository[outputId]) return '';

    const inputsIdsAsAttributes = inputIds.map((str, i) => `a${i}="${str}"`);
    return (`
        <command name="${commandName}">
            <input ${inputsIdsAsAttributes.join(' ')}/>
            <output a0="${outputId}"/>
        </command>
        ${additionalElementData ?
            `<element type="${outputType}" label="${outputId}">
                ${additionalElementData.join('\n')}
            </element>` :
            `<element type="${outputType}" label="${outputId}"/>`}
        `
    );
}

function transformFreePointXml(inputXmlElement, freePointGenerator) {
    // the first character in geoproof IDs is always a $, so cut that
    const pointId = cleanId(inputXmlElement.getAttribute('id'));
    const coords = freePointGenerator.next().value;
    if (!coords) fatalError("Not enough free point coordinates available");
    return (
        `<element type="point" label="${pointId}">
            <coords x="${coords[0]}" y="${coords[1]}" z="1.0"/>
            <objColor r="50" g="50" b="200" alpha="0.0"/>
        </element>`
    );
}

function transformCommandXml(inputXmlElement) {
    // the first character in geoproof IDs is always a $, so cut that
    const outputId = cleanId(inputXmlElement.getAttribute('id'));
    const outputType = inputXmlElement.nodeName.toLowerCase();
    const commandCallStr = inputXmlElement.textContent;
    if (!commandCallStr) return '';
    const command = parseCommand(commandCallStr);
    console.log(`command ${command.name} with inputs ${command.inputs} and output ${outputId}`);
    const mappedCommandName = commandNameMapping[command.name];
    if (!mappedCommandName) {
        if (command.name in commandMacroMapping) {
            return commandMacroMapping[command.name](...command.inputs, outputId);
        }
        fatalError("Unknown command " + command.name);
    }
    return createCommandXml(mappedCommandName, command.inputs, outputId, outputType);
}

function transformPropXml(inputXmlElement) {
    const commandCallStr = inputXmlElement.textContent;
    if (!commandCallStr) return '';
    const command = parseCommand(commandCallStr);
    console.log(`prop ${command.name} with inputs ${command.inputs}`);
    if (command.name in propMacroMapping) {
        return propMacroMapping[command.name](...command.inputs);
    } else {
        fatalError("Unknown prop " + command.name);
    }
    return '';
}

function fatalError(msg) {
    alert(msg);
    throw msg;
}

function resetData() {
    macroIdRepository = {};
    propExplanations = [];
}

// removes leading $
// fixes subscript for multi-digit numbers (by putting underscores in front every digit after the first one)
function cleanId(originalId) {
    return originalId.substr(1).replace(/\d+/g, (match) => match.split('').join('_'));
}

function parseCommand(commandStr) {
    const uncleanInputIds = commandStr.substring(commandStr.indexOf('[') + 1, commandStr.indexOf(']')).split(',');
    return {
        name: commandStr.substring(0, commandStr.indexOf('[')),
        inputs: uncleanInputIds.map(str => str.trim()).filter(str => str.startsWith('$')).map(cleanId)
    }
}
