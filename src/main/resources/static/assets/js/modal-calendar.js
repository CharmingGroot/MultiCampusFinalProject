<input type="text" id="inp">
    <p onclick="openChild()" style="width: 30%;" class="parents">자식창열기</p>
    <p onclick="alertByChild()" style="width: 30%;" class="child">자식창에서 부모창에 alert 띄우기</p>
    <p onclick="alertByParents()" style="width: 30%;" class="parents">부모창에서 자식창에 alert 띄우기</p>
    <div id="log" class="note"></div>
    
    <script>
        let childWindow;

        if(opener){
            $('.parents').forEach(e => e.style.display='none');
        }else{
            $('.child').style.display='none';
        }

        let alertByChild = () => {
            opener.alert($('#inp').value);
            //opener.document.querySelector('#inp').innerHTML += `from child : ${$('#inp').value} <br>`;
            $.apply(opener, ['#log',[`from child : ${$('#inp').value}`]]);
	    }

	    let alertByParents = () => {
            if(!childWindow) {
                alert('자식창이 없습니다.');
            }

            childWindow.alert($('#inp').value);
            //childWindow.document.querySelector('#inp').innerHTML += `from parents : ${$('#inp').value} <br>`;
            $.apply(childWindow, ['#log',[`from parents : ${$('#inp').value}`]]);
	    }

