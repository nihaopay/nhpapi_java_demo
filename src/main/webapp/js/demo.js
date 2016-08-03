$(function() {
    $('.datetimepicker').datetimepicker();
    $("#btnSubmit").click(function(event) {
        event.preventDefault();
        if($("#url").val()){
            var post_url = $("#url").val();
        }else{
            var post_url = "/"+$("#apiDebugForm").attr("action");
        }
        $("#apiRequestUrlInfo").html('[ ' + post_url + ' ]');
        $("#requestBody").empty().html(getRequestBody());
        $("#responseBody").empty().append('Loading...');
        var ajaxTime = new Date().getTime();
        $.ajax({
            type : "POST",
            dataType : "json",
            url : $("#apiDebugForm").attr("action"),
            data : $("#apiDebugForm").serialize(),
            success : function(data, textStatus, xhr) {
                var totalTime = (new Date().getTime() - ajaxTime) / 1000;
                if (xhr.status){
                    var http_status = xhr.status;
                }else{
                    var http_status = 502;
                }
                var apiRequestInfo = {
                    "exe_time" : totalTime,
                    'http_status' : http_status,
                    'text_status': textStatus
                };
                jsonview(apiRequestInfo, $("#apiRequest"));
                jsonview(data, $("#responseBody"));
            },
            error : function(data, textStatus, xhr) {
                var totalTime = (new Date().getTime() - ajaxTime) / 1000;
                if (xhr.status){
                    var http_status = xhr.status;
                }else{
                    var http_status = 502;
                }
                var apiRequestInfo = {
                    "exe_time" : totalTime,
                    'http_status' : http_status,
                    'text_status': textStatus
                };
                jsonview(apiRequestInfo, $("#apiRequest"));
                console.log(data);
                $("#responseBody").html("error:<pre>" + stripHTML(data.responseText)+"</pre>");
            }
        });
    });
    $("#btnReset").click(function(){
        $("req").each(function() {   
              this.reset();   
             });   
   })
});

function stripHTML(dirtyString) {
    var container = document.createElement('div');
    var text = document.createTextNode(dirtyString);
    container.appendChild(text);
    return container.innerHTML; // innerHTML will be a xss safe string
}

function getRequestBody() {
    var form_data_list = $("#apiDebugForm").serializeArray();
    var result = "";
    $.each(form_data_list, function(i, entry) {
        if (entry.name != 'url' && entry.name != 'token') {
            /*if (result != "") {
                result += "&" + entry.name + '=' + entry.value;
            } else {
                result += "" + entry.name + '=' + entry.value;
            }*/
            if (result != "") {
                result +=  entry.name + '=' + entry.value+"\n<br>";
            } else {
                result += "" + entry.name + '=' + entry.value+"\n<br>";
            }
        }
    });
    return result;
}
