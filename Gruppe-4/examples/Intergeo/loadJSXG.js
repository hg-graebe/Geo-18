function loadJSXG()
{
	var selection = document.getElementById("selection");
	fname = "http://localhost:8000/examples/Intergeo/" + selection.options[selection.selectedIndex].value.toString();

    $('debug').innerHTML = fname+'<br>';
    board = JXG.JSXGraph.loadBoardFromFile('jxgbox', fname , 'Intergeo');
}