	
	let speice_str = document.getElementById("speices");
	let breed_str = document.getElementById("breed");
	let age_str = document.getElementById("age");
	
	function createAnswer() {
		const api_key = "";
		let question = "";
		
		if(speice_str.options[speice_str.selectedIndex].value == "cat" || speice_str.options[speice_str.selectedIndex].value == "dog"){
			question = speice_str.options[speice_str.selectedIndex].value 
						+ breed_str.options[breed_str.selectedIndex].value 
						+ age_str.options[age_str.selectedIndex].value 
						+ document.getElementById('aiQuestion').value;
		} else {
			question = speice_str.options[speice_str.selectedIndex].value 
						+ age_str.options[age_str.selectedIndex].value 
			
						+ document.getElementById('aiQuestion').value;
		}
		
		const messages = [
			{role:'system', content: 'You are a helpful assistant.'},
			{role:'user', content: question}
		]
		const data = {
				model: "gpt-3.5-turbo",
			  	messages: messages
		}
		
		const config = {
				method: 'POST',
				headers: {
					Authorization : `Bearer ${api_key}`,
					'Content-Type' : 'application/json'
				},
		
				body: JSON.stringify(data)
		}
		
		let spinner = document.getElementById("roadingStatus");
		
		spinner.innerHTML='<div class="spinner-border" role="status"></div>';	
		
		fetch('https://api.openai.com/v1/chat/completions',config)
		.then(response => response.text()) 
		.then(text => { 
			let data = JSON.parse(text);
			let content =" A. " + data.choices[0].message.content+"\n";
			document.getElementById("result").innerHTML='<div class="card" style="word-break:break-all;">'+content+'</div>'; 
			})
		.then(()=> {
			document.getElementById("roadingStatus").style.display="none"
			})
		
		.catch(function (error) {
			console.error(error)
			let errorMessage="다시 시도해주세요.";
			document.getElementById("result").append(errorMessage);})
		
		}
	