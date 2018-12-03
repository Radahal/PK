function generateArticleList(list) {
	$("#result-list").empty();
	path = "/search/corpseDir/"
	var j = 0;
	for (var key in list) {
		filename=key;
		title_tmp=key.split("_");
		title=""
		for(i=1; i<title_tmp.length;i++)
			title=title+title_tmp[i]+" "
		description="";
		value=list[key];
		filePath=path+filename;
		$("#result-list").append('<div class="listitem"><div class="left-box"><h2><a href="'+filePath+'" target="_blank">'+title+'</a></h2><p>'+description+'</p></div><div class="right-box"><p>'+value+'</p></div></div>');
		j++;
		
	}
	$("#counter").empty()
	$("#counter").append(j)
	console.log("done")
}

$(document).ready(function () {
	var lemmatize = false;
	var stopwords = false;

	$("input[name='lemma']").change(function () {
		lemmatize = !lemmatize;
	});

	$("input[name='stopwords']").change(function () {
		stopwords = !stopwords;
		if (stopwords) {
			document.getElementById("stopwords_val").disabled = false;
			document.getElementById("stopwords_val").value = 1;

		} else {
			document.getElementById("stopwords_val").disabled = true;
		}
	});

	$("#btn").click(function () {
		var search_text = $("input[name='searchfield']").val();
		var stopwords_val = 0;
		if (stopwords)
			stopwords_val = $("input[name='stopwords_val']").val();
		if (search_text != "") {
			console.log(search_text + ", " + lemmatize + ", " + stopwords + ", " + stopwords_val);

			data_obj = {
				search: search_text,
				lemmatize: lemmatize,
				stopwords: stopwords,
				stopwords_val: stopwords_val
			}

			$.ajax({
				url: "/search/scripts/python/search.py",
				type: "POST",
				datatype: "json",
				data: data_obj,
				success: function (response) {
					//console.log(response.data);
					generateArticleList(response.data)
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					console.log("Status: "+textStatus);
					console.log("Error: "+errorThrown);
				}
				
			});
		};

	});
});