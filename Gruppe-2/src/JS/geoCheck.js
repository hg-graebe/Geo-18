var b = JXG.JSXGraph.initBoard("jxgbox",{boundingbox: [-5, 5, 5, -5], axis:true});
var p0 = b.create("point",[-388380553522791/625000000000000,2053351100363973/1000000000000000], {name:"A",size: 4, face: "o"});
var p1 = b.create("point",[4,-4264724801648871/2500000000000000], {name:"B",size: 4, face: "o"});
var p2 = b.create("point",[4,-66855595497939/125000000000000], {name:"C",size: 4, face: "o"});
var l0 = b.create("line",["B","A"], {strokeColor:"#00ff00",strokeWidth:2});
var l1 = b.create("line",["C","B"], {strokeColor:"#00ff00",strokeWidth:2});
var pl0 = b.create("parallel",["a","C"], {strokeColor:"#00ff00",strokeWidth:2});
var c0 = b.create("circle",["B","A"], {strokeColor:"#00ff00",strokeWidth:2});
