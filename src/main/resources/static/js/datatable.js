$(document).ready( function () {
	 var table = $('#Contragent').DataTable({
			"sAjaxSource": "/getAllContragent",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
		          { "mData": "name" },
				  { "mData": "sequence" }
			]
	 })
});