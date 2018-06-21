function loadJSXG(path)
{
	var selection = document.getElementById("selection");
	fname = "http://localhost:8000/" + path + selection.options[selection.selectedIndex].value.toString();

    $('debug').innerHTML = fname+'<br>';
    board = JXG.JSXGraph.loadBoardFromFile('jxgbox', fname , 'Intergeo');
}