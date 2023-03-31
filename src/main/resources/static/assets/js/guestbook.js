
// 페이지 로드 시 삭제버튼, 수정버튼 세팅함수 실행
window.onload = () => {
  delBtnEventSet();
  updateBtnEventSet();
}



const commentForm = document.getElementById('commentForm');
// console.log(commentForm);
commentForm.addEventListener('submit', async (event) => {
  event.preventDefault();

  let commentForm = document.getElementById("commentForm");

  let comment = document.commentForm.content.value;

  // 굳이 formData나 객체형태로 변환할 필요없이 값만 전달해도 된다.
  // const formData = new FormData();
  // formData.append('comment', comment);
  // const data = { comment: comment };

  await fetch('http://localhost:8080/guestbook/upload', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: comment,
  })
    .then((response) => response)
    .then((data) => {
      console.log('성공:', data);
    })
    .then(() => {
      location.reload();
    })
    .catch((error) => {
      console.error('실패:', error);
    });



});



function addComment() {

  // console.log("addComment");

  // //ul
  // const guestCommentWrapper = document.getElementById("guestCommentWrapper");

  // let createList = document.createElement("li");
  // let createDiv1 = document.createElement("div"); // dept 1
  // let createDivS1 = document.createElement("div"); // dept 2 -1
  // let createDivS2 = document.createElement("div"); // dept 2 -2
  // let createDivS3 = document.createElement("div"); // dept 2 -3
  // let createDivT1 = document.createElement("div"); // dept 3 -1
  // let createDivT2 = document.createElement("div"); // dept 3 -2
  // let createBtn1 = document.createElement("button");
  // let createBtn2 = document.createElement("button");




  // createDiv1.classList.add("card", "col-12");
  // createDivS1.classList.add("comment-info-wrapper");
  // createDivS2.classList.add("card-body");
  // createDivS3.classList.add("btnWrapper", "align-self-end");
  // createBtn1.classList.add("btn", "btn-sm", "delBtn", "btn-outline-dangerous");
  // createBtn2.classList.add("btn", "btn-sm", "updateBtn", "btn-outline-infoo");

  // createDivS2.innerText = "반가워";
  // createDivT1.innerText = "보낸이 : 똘이";
  // createDivT2.innerText = "등록일자: 2033.12.25";


  // createBtn1.setAttribute("type", "button");
  // createBtn1.setAttribute("style", "border-top-right-radius: 0; border-top-left-radius: 0;");
  // // createBtn1.id = "btnDelete";
  // createBtn1.innerText = "삭제";

  // createBtn2.setAttribute("type", "button");
  // createBtn2.setAttribute("style", "border-top-right-radius: 0; border-top-left-radius: 0;");
  // // createBtn2.id = "btnUpdate";
  // createBtn2.innerText = "수정";

  // createDivS1.append(createDivT1);
  // createDivS1.append(createDivT2);

  // createDivS3.append(createBtn2);
  // createDivS3.append(createBtn1);


  // createDiv1.append(createDivS1);
  // createDiv1.append(createDivS2);
  // createDiv1.append(createDivS3);


  // createList.append(createDiv1);

  // guestCommentWrapper.append(createList);


  // 리스트가 만들어질때마다 해당 리스트의 삭제버튼에 onclick 붙이기
  delBtnEventSet();

  // 리스트가 만들어질때마다 해당 리스트의 수정버튼에 onclick 붙이기
  updateBtnEventSet();

};
















// 삭제,수정 버튼에 속성부여해주는 함수들


function delBtnEventSet() {
  let eventTarget = document.getElementsByClassName("delBtn");
  // console.log(eventTarget);
  for (i = 0; i < eventTarget.length; i++) {
    eventTarget[i].setAttribute("onclick", "deleteList(this)");
  };

};

function updateBtnEventSet() {
  let eventTarget = document.getElementsByClassName("updateBtn");
  // console.log(eventTarget);
  for (i = 0; i < eventTarget.length; i++) {
    eventTarget[i].setAttribute("onclick", "updateCommentForm(this)");
  };
};



















// 비동기로 동작해야하며, 해당 함수 호출 시 DB 내용이 업데이트되어야함.
function deleteList(e) {

  console.log("deleteList 실행");

  // 1. 시각적으로 list지우기.
  // 2. DB에서 list 지우기.

  // gbIdx값과 일치하는 행 지우기.
  let gbIdx = e.parentElement.parentElement.firstElementChild.firstElementChild.innerText;




  // 이렇게 여러번 접근하는것이 맞는건가 ^^.. no! parentElement로 접근해야함.
  let parent = e.parentNode.parentNode.parentNode;
  // parent.parentNode.removeChild(parent);

  let dataObj = {
    gbIdx: gbIdx,
  }

  deleteComment(dataObj);


};


// 수정 폼을 만들고, 서버로 비동기 put요청을 보내는 함수
function updateCommentForm(e) {

  // 기존에 입력되어있던 값 가져와서 임시 변수에 저장.
  // 변경값을 입력받을 엘리먼트 생성

  // gbIdx 값
  let gbIdx = e.parentElement.parentElement.firstElementChild.firstElementChild.innerText;
  // console.log(gbIdx);


  let content = e.parentElement.parentElement.firstElementChild.nextElementSibling.firstElementChild.innerText;
  // console.log(content);

  let data = {
    gbIdx: gbIdx,
    content: content,
  };



  // test
  console.log("updateCommentForm 실행");
  // console.log(gbIdx);
  // console.log(content);
  // console.log(e.parentElement.parentElement.parentElement);
  // console.log(data.gbIdx + data.content);



  // 현재 li 비우기
  let li = e.parentElement.parentElement.parentElement;
  console.log(li.parentElement);
  li.innerHTML = "";


  //form action="/guestbook/update" method="put" 
  li.innerHTML = `<div class="comment-form-wrapper1"><form enctype="multipart/form-data" class="comment-form-wrapper2">
  <div class="form-floating col-11">
  <textarea class="form-control" id="updateTextArea" cols="50" rows="5" style="height: 200px; resize:none; border-top-right-radius: 0.7rem; border-top-left-radius: 0.7rem; border-bottom-left-radius: 0.7rem; border-bottom-right-radius: 0.7rem;" placeholder="내용을 입력하세요.">${data.content}</textarea>
  </div>
  <div class="d-grid gap-1 col-1">
  <button class="btn btn-primary btn-lg btn-outline-infoo" type="button" id="updateCommentBtn" style="border-top-right-radius: 0.7rem; border-top-left-radius: 0; border-bottom-left-radius: 0; border-bottom-right-radius: 0;">수정</button>
  <button class="btn btn-primary btn-lg btn-outline-dangerous" type="button" id="cancelBtn" style="border-top-right-radius: 0; border-top-left-radius: 0; border-bottom-left-radius: 0; border-bottom-right-radius: 0.7rem;">취소</button>
  </div>
  </form></div>`;

  let updateCommentBtn = document.getElementById("updateCommentBtn");

  // 수정버튼
  updateCommentBtn.addEventListener('click', (e) => {
    e.preventDefault();



    let content = document.getElementById("updateTextArea").value;
    console.log(document.getElementById("updateTextArea").value);


    let dataObj = {
      content: content,
      gbIdx: data.gbIdx,
    };

    console.log(data);
    console.log(dataObj)
    console.log("updateCommentBtn.addEventListener");

    updateComment(dataObj, e);

  });

  // 수정취소버튼
  let cancelBtn = document.getElementById("cancelBtn");

  cancelBtn.addEventListener('click', () => {
    location.reload();
  });
}






function updateComment(dataObj, e) {

  console.log("updateComment 실행");
  console.log(dataObj);
  // 비동기 put
  // fetch PUT 메서드로 기존 테이블의 값 변경하고 비동기 통신하여 리렌더링 요청
  fetch('http://localhost:8080/guestbook/update', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(dataObj),
  })
    .then((response) => response)
    .then((data) => {
      console.log('성공:', data);
    })
    .then(() => {
      location.reload();
    })
    .catch((error) => {
      console.error('실패:', error);
    });


  // console.log('e : ' + e);
  // console.log('this : ' + this);
  // let li = e.parentElement;
  // console.log(li);

  // li.innerHTML = `<li th:each="guestbook : ${guestbookList}">
  //   <div class="card col-12">
  //     <div class="comment-info-wrapper">
  //       <div class="gbIdx" th:text="${guestbook.gbIdx}"></div>
  //       <div th:text="${guestbook.nickname}"></div>
  //       <div th:text="${guestbook.regDate}"></div>
  //     </div>
  //     <div class="card-body">
  //       <p name="comment" th:text="${guestbook.content}"></p>
  //     </div>
  //     <div class="btnWrapper align-self-end">
  //       <button type="button" class="btn btn-outline-infoo btn-sm updateBtn" style="border-top-right-radius: 0; border-top-left-radius: 0;" id="btnUpdate">수정</button>
  //       <button type="button" class="btn btn-outline-dangerous btn-sm delBtn" style="border-top-right-radius: 0; border-top-left-radius: 0;" id="btnDelete">삭제</button>
  //     </div>
  //   </div>
  // </li>`

}

function deleteComment(dataObj) {

  console.log("deleteComment 실행");
  console.log(dataObj);
  // 비동기 put
  // fetch PUT 메서드로 기존 테이블의 값 변경하고 비동기 통신하여 리렌더링 요청
  fetch('http://localhost:8080/guestbook/delete', {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(dataObj),
  })
    .then((response) => response)
    .then((data) => {
      console.log('성공:', data);
    })
    .then(() => {
      location.reload();
    })
    .catch((error) => {
      console.error('실패:', error);
    });
}

