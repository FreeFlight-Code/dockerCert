'use strict';

var chatPage = document.querySelector('#chat-page');
var nameForm = document.querySelector('#nameForm');
var nameInput = document.querySelector('#nameInput');
var responseArea = document.querySelector('#responseArea');
var connectingElement = document.querySelector('.connecting');
var sendButton = document.querySelector("#send");
var clearButton = document.querySelector("#clear");

var stompClient = null;

function connect(event) {

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    if (stompClient.connected) {
    	stompClient.subscribe('/topic/response', onMessageReceived);
    	connectingElement.classList.add('hidden');
    }

}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendName(event) {
	console.log("event", event);
    var nameContent = nameInput.value.trim();
    if(nameContent && stompClient.connected) {
    	sendHello(nameContent);
    } else {
    	connect(event);
    	sendHello(nameContent);
    }
    event.preventDefault();
}

function sendHello(name) {
    stompClient.send("/app/hello", {}, JSON.stringify({name: name}));
}

function onMessageReceived(payload) {
    var response = JSON.parse(payload.body);

    var nameElement = document.createElement('li');

    var textElement = document.createElement('p');
    var nameText = document.createTextNode(response.message);
    textElement.appendChild(nameText);

    nameElement.appendChild(textElement);

    responseArea.appendChild(nameElement);
    responseArea.scrollTop = responseArea.scrollHeight;
}

function clear(event) {
	while (responseArea.firstChild) {
	    responseArea.removeChild(responseArea.firstChild);
	}
	responseArea.removeChildren();
}

function enterKey(event) {
	event.preventDefault();

	if (event.keyCode === 13) {
		sendButton.click();
	}	
}

sendButton.addEventListener('click', sendName, true);
clearButton.addEventListener('click', clear, true);
document.addEventListener('load', connect, true);
nameInput.addEventListener('keyup', enterKey, true);