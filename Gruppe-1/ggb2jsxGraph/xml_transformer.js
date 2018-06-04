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

// construction tools that are entirely equivalent between Geoproof/GeoGebra, including parameters and their order
const commandNameMapping = {
    'p_bisector': 'LineBisector',
    'intersection_point': 'Intersect',
    'midpoint': 'Midpoint',
    'pp_line': 'Line',
    'ortho_line': 'OrthogonalLine',
    'par_line': 'Line'
};
// any construction tools not covered by the above
// callback arguments are always (...inputs, output)
const commandMacroMapping = {
    'altitude': (pointTop, pointBase1, pointBase2, finalOutput) => {
        const lineId = `base of ${finalOutput}`;
        return createCommandXml('Line', [pointBase1, pointBase2], lineId, 'line') + 
            createCommandXml('OrthogonalLine', [pointTop, lineId], finalOutput, 'line');
    }
};

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
    yield [2, 0.5];
    yield [0.7, 1.7];
}

function createCommandXml(commandName, inputIds, outputId, outputType) {
    const inputsIdsAsAttributes = inputIds.map((str, i) => `a${i}="${str}"`);
    return (`
        <command name="${commandName}">
            <input ${inputsIdsAsAttributes.join(' ')}/>
            <output a0="${outputId}"/>
        </command>
        <element type="${outputType}" label="${outputId}"/>`
    );
}

function transformFreePointXml(inputXmlElement, freePointGenerator) {
    // the first character in geoproof IDs is always a $, so cut that
    const pointId = inputXmlElement.getAttribute('id').substr(1);
    const coords = freePointGenerator.next().value;
    if (!coords) throw "Not enough free point coordinates available";
    return (
        `<element type="point" label="${pointId}">
            <coords x="${coords[0]}" y="${coords[1]}" z="1.0"/>
        </element>`
    );
}

function transformCommandXml(inputXmlElement) {
    // the first character in geoproof IDs is always a $, so cut that
    const outputId = inputXmlElement.getAttribute('id').substr(1);
    const outputType = inputXmlElement.nodeName.toLowerCase();
    const commandCallStr = inputXmlElement.textContent;
    if (!commandCallStr) return '';
    const commandName = commandCallStr.substring(0, commandCallStr.indexOf('['));
    const uncleanInputIds = commandCallStr.substring(commandCallStr.indexOf('[') + 1, commandCallStr.indexOf(']')).split(',');
    const inputIds = uncleanInputIds.map(str => str.trim().substr(1));
    console.log(`command ${commandName} with inputs ${inputIds} and output ${outputId}`);
    const mappedCommandName = commandNameMapping[commandName];
    if (!mappedCommandName) {
        if (commandName in commandMacroMapping) {
            return commandMacroMapping[commandName](...inputIds, outputId);
        }
        throw "Unknown command " + commandName;
    }
    return createCommandXml(mappedCommandName, inputIds, outputId, outputType);
}

function transformPropXml(inputXmlElement) {
    return '';
}
