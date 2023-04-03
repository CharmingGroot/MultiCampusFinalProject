function showChat() {
		let chatMessage = document.getElementById("msg").value;
		let chatBox = '<div class="d-flex flex-row justify-content-end">' + '<div>' +
       '<p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">' + chatMessage  + '</div>' + '</div>';
		
		document.getElementById("response").innerHTML += chatBox;
	}
	
		document.getElementById("button-send").addEventListener("click", function() {
		showChat();
		});

var username = $("#authentication.principal.userId");
var stompClient = null;
	
	function connect() {
	    var socket = new SockJS('/gs-guide-websocket');
	    stompClient = Stomp.over(socket);
	    
	    stompClient.connect({}, function (frame) {
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/chatting/' + '[[${chatmember}]]', function (msg) {
	          	res = JSON.parse(msg.body);
	          	console.dir(res)
	           	showChat();
	        });
	    });
	}
	
	function disconnect() {
	    if (stompClient !== null) {
	        stompClient.disconnect();
	    }
	    console.log("Disconnected");
	}
	
	function sendMessage() {
		let message = msg.value;
		
	    stompClient.send("/app/chatting/" + '[[${chatmember}]]', {}, JSON.stringify({'message':message}));
	}
	
	connect();