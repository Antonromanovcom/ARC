<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Ws problem</title>
</head>
<body>
<a href="#" id="send">Request user specific msg</a><br/><br/>
Received Message To Me Only: <span id="received"></span><br/><br/>


<div id="hello"><h1>Hello Vanilla!</h1></div>

<%--??? ????????? --%>

<div id="history" style="height:300px; width:500px; background:#F7E190; border: 1px solid black; white-space: pre">145
</div>
<div style="height:30px"></div>

<%--?????? ?????????--%>

<div style="height:10px"></div>
<div class="input-message">
    <input type="text" id="message" autocomplete="off"
           placeholder="Type a message..."/>
    <button id="sendto">Send</button>
</div>


<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<script src="resources/stomp.js"></script>
<script>
    $(document).ready(function () {
        // console.log('? ??? - ' + whoIam());
        var userName = '';
        var localPath = '/restapi/users/whoiam';
        var remotePath = '/springwithoutxml/restapi/users/whoiam';
        var remoteSpocketPath = '/springwithoutxml';
        var localSpocketEndPointPath = '/chat';
        var localSocketUserPath = '/user/';
        var serverMessageBrokerPath = '/app/chat';

       // serverMessageBrokerPath = remoteSpocketPath + '/app/chat';
        localSpocketEndPointPath = remoteSpocketPath + '/chat';
        //localSocketUserPath = remoteSpocketPath + '/user/';
        console.log('local Socket User Path: ' +  localSocketUserPath);
        localPath = remotePath;

        console.log('End Point: ' +  localSpocketEndPointPath);
       // console.log('local Socket User Path without reply: ' +  localSocketUserPath + userName);
       //  console.log('local Socket User Path: ' +  localSocketUserPath + userName + '/reply');
        console.log('Server Message Broker Path: ' +  serverMessageBrokerPath);

        var socket = new SockJS(localSpocketEndPointPath);
        var stompClient = Stomp.over(socket);

        console.log('GET query');

        $.get(localPath, {async: false}, function (data) {
        //$.get("/restapi/users/whoiam", {async: false}, function (data) {
            $('#received').html(data);
            console.log(data);
            userName = data;
            console.log(userName);

            stompClient.connect('guest', 'guest', function (frame1) {
                stompClient.subscribe(localSocketUserPath + userName + '/reply', function (frame2) {

                    console.log(frame2);
                    console.log(frame2.body);

                    var msg = frame2.body;
                    console.log(msg);
                    $('#received').html(msg);
                    addMessage(msg);
                });
            }, function () {
                alert(error.headers.message);
            });


        }).fail(function () {
            alert("error");
        });


        function addMessage(message) {

            var node = document.getElementById('history');
            htmlContent = node.innerHTML;
            console.log(htmlContent);

            $('#history').text(htmlContent + '\n\r' + message);

        }

        function whoIam() {

            var whoiam = '';
            console.log('GET query');


            $.get(localPath, {async: false}, function (data) {
            //$.get("/restapi/users/whoiam", {async: false}, function (data) {
                $('#received').html(data);
                console.log(data);
                whoiam = data;
                //alert (whoiam);
            }).fail(function () {
                alert("error");
            });
            console.log('I am in func - ' + whoiam);
            return whoiam;

        }

        $('#sendto').click(function (e) {
            e.preventDefault();
            console.log('I am sending...');
            stompClient.send(serverMessageBrokerPath);

            $.get(localPath, function (data) {
            //$.get("/restapi/users/whoiam", function (data) {
                $('#received').html(data);
            });

        });

        $('#send').click(function (e) {

            console.log('GET query');

            $.get(localPath, function (data) {
            //$.get("/restapi/users/whoiam", function (data) {
                $('#received').html(data);
                console.log(data);
            }).fail(function () {
                alert("error");
            });
        });
    });

</script>
</body>
</html>