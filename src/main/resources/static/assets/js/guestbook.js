// let textFormBtn = document.getElementById("textFormBtn");

// textFormBtn.addEventListener('click', () => {

//   // let inputComment = this.comment.value;
//   var commentForm = document.commentForm;
//   console.log(commentForm);
//   var comment = commentForm.comment.value;
//   console.log(comment);
// })



// onload될 때 받아온 데이터를 변수에 저장 
// 지금은 더미데이터
let guestName = ["최호근1", "최호근2"];
let commentNumber = ["1", "2"];
let commentText = ["스프링부트는 어렵다", "자바스크립트는 재미있다"];

let guestBookData = {
  commentNumber: commentNumber,
  guestName: guestName,
  commentText: commentText
};


window.onload = () => {
  // fetch로 글번호, 작성자 댓글 가져와서 전역 변수값을 초기화.
  // 해당 정보들을 받아 addComment함수로 li 만들기

  // 반복문으로 생성하기.


}





function addComment() {
  let guestCommentWrapper = document.getElementById('guestCommentWrapper');


  // 최상위
  let guestComment = document.createElement("li");
  guestComment.classList.add("guestComment");


  // 차상위
  let guestListLi = document.createElement("div");
  guestListLi.classList.add("guest-li");

  // 3
  let guestListInner = document.createElement("div");
  guestListInner.classList.add("guest-li-inner");

  // 4
  let guestListInfo = document.createElement("div");
  guestListInfo.classList.add("guest-info", "test");

  // 5 - 1
  let commentNumber = document.createElement("div");
  commentNumber.classList.add("commentNumber", "col-1");
  commentNumber.innerText = "no. 1";

  let commentedGuest = document.createElement("div");
  commentedGuest.classList.add("commentedGuest", "col-3");
  commentedGuest.innerText = "작성자 : 콩순이 칭구";

  let commentDateTime = document.createElement("div");
  commentDateTime.classList.add("commentDateTime", "col-7");
  commentDateTime.innerText = "23.01.30";

  let deleteCommentBtn = document.createElement("button");
  deleteCommentBtn.classList.add("btn-primary", "col-1", "delBtn");
  // deleteCommentBtn.id = "deleteCommentBtn";
  deleteCommentBtn.setAttribute("type", "button");
  // deleteCommentBtn.setAttribute("onclick", "del()");
  deleteCommentBtn.innerText = "X";


  // 4
  let guestCommentForm = document.createElement("div");
  guestCommentForm.classList.add("guestCommentForm", "test");


  // 5 - 2
  let guestProfile = document.createElement("div");
  guestProfile.classList.add("guestProfile", "test");

  let formWrapper = document.createElement("div");
  formWrapper.classList.add("formWrapper", "test");

  // 6

  formWrapper.appendChild(document.createElement("div")).classList.add("guestCommentResult");




  guestCommentForm.append(guestProfile)
  guestCommentForm.append(formWrapper);

  guestListInfo.append(commentNumber);
  guestListInfo.append(commentedGuest);
  guestListInfo.append(commentDateTime);
  guestListInfo.append(deleteCommentBtn);


  guestListInner.append(guestListInfo);
  guestListInner.append(guestCommentForm);


  guestListLi.append(guestListInner);

  guestComment.append(guestListLi);

  // document.body.append(guestList);

  guestCommentWrapper.append(guestComment);

  btnEventCount();
  liCount();
};


function btnEventCount() {
  // 버튼
  let eventTarget = document.getElementsByClassName("delBtn");

  // 삭제할 리스트
  let guestComment = document.getElementsByClassName("guestComment");

  console.log("btnEventCount 실행");
  for (i = 0; i < eventTarget.length; i++) {
    eventTarget[i].addEventListener('click', (e) => {
      guestComment.remove();

      i--;
    });
  };
};

function liCount() {

}




// 비동기로 동작해야하며, 해당 함수 호출 시 DB 내용이 업데이트되어야함.
// let deleteBtn = document.getElementById("deleteCommentBtn");
// let deleteArea = document.getElementById("deleteArea");
// deleteBtn.addEventListener("click", () => {
//   console.log("hi del");
//   deleteArea.remove();
// });

// function del() {
//   deleteArea.remove();
// }










// 비동기로  작성자, form데이터, 작성일시를 서버로 보내기
function submitInputText() {

  let formTextArea = document.getElementById("formTextArea").value;
  console.log(formTextArea);

  const data = { InputText: formTextArea };

  fetch('http://localhost:8080/blog/guestbook', {
    method: 'POST', // 또는 'PUT'
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log('성공:', data);
    })
    .then(window.onload())
    .catch((error) => {
      console.error('실패:', error);
    });

}



