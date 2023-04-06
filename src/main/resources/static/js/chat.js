function showChat() {
		let chatMessage = document.getElementById("msg").value;
		let chatBox = '<div class="d-flex flex-row justify-content-end">' + '<div>' +
       '<p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">' + chatMessage  + '</div>' + '</div>';
		
		document.getElementById("response").innerHTML += chatBox;
	}
	
		document.getElementById("button-send").addEventListener("click", function() {
		showChat();
		});

var stompClient = null;
	
	function connect() {
	    const socket = new SockJS('http://localhost:8080/gs-guide-websocket', {
							    transports: ['websocket'],
							    iframe: false
							});

	    stompClient = Stomp.over(socket);
	    
	    stompClient.connect({}, function (frame) {
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/chatting/' + [[${cgIdx}]], function (msg) {
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
		
	    stompClient.send("/app/chat-room/" + [[${cgIdx}]], {}, JSON.stringify({'message':message}));
	}
	
	connect();