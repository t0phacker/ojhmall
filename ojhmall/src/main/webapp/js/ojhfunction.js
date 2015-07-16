function search() {
	var sch_value = jQuery('#form_search #prdName').val();
	if (sch_value == '') {
		alert('검색어를 입력하세요.');
	} else {
		jQuery('#form_search').submit();
	}
}
function test(){
	alert("test success");
}
function plusPrdAmount(id)
{
    var prdAmount = document.getElementById(id).value;
    document.getElementById(id).value = parseInt(prdAmount) + 1;
}
function minusPrdAmount(id)
{
    var prdAmount = document.getElementById(id).value;
    document.getElementById(id).value = parseInt(prdAmount) - 1;
}