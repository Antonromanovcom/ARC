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

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<script src="resources/stomp.js"></script>
<script>
    $(document).ready(function () {
        var socket = new SockJS("/chat");
        var stompClient = Stomp.over(socket);
        var userName = "bob";


        // connect via websocket. username password here should be ignore
        // as we're using spring security
        stompClient.connect('guest', 'guest', function (frame1) {
            // connect callback
            // subscribe to
            stompClient.subscribe('/user/' + userName + '/reply', function (frame2) {
                console.log(frame2);
                console.log(frame2.body);
                //var msg = JSON.parse(frame2.body);
                var msg = frame2.body;
                console.log(msg);
                $('#received').html(msg);
            });
        }, function () {
            // error callback
            alert(error.headers.message);
        });

        // Send websocket to server when clicked
        $('#send').click(function (e) {
            e.preventDefault();
            console.log('I am sending...');
            stompClient.send('/app/chat');
        });
    });
</script>
</body>
</html>