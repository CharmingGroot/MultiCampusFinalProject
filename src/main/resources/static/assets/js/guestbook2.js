
window.onload = () => {

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
    .catch((error) => {
      console.error('실패:', error);
    });



});



function addComment() {

  console.log("addComment");

  //ul
  const guestCommentWrapper = document.getElementById("guestCommentWrapper");

  let createList = document.createElement("li");
  let createDiv1 = document.createElement("div"); // dept 1
  let createDivS1 = document.createElement("div"); // dept 2 -1
  let createDivS2 = document.createElement("div"); // dept 2 -2
  let createDivS3 = document.createElement("div"); // dept 2 -3
  let createDivT1 = document.createElement("div"); // dept 3 -1
  let createDivT2 = document.createElement("div"); // dept 3 -2
  let createBtn1 = document.createElement("button");
  let createBtn2 = document.createElement("button");




  createDiv1.classList.add("card", "col-12");
  createDivS1.classList.add("comment-info-wrapper");
  createDivS2.classList.add("card-body");
  createDivS3.classList.add("btnWrapper", "align-self-end");
  createBtn1.classList.add("btn", "btn-sm", "delBtn", "btn-outline-dangerous");
  createBtn2.classList.add("btn", "btn-sm", "updateBtn", "btn-outline-infoo");

  createDivS2.innerText = "반가워";
  createDivT1.innerText = "보낸이 : 똘이";
  createDivT2.innerText = "등록일자: 2033.12.25";


  createBtn1.setAttribute("type", "button");
  createBtn1.setAttribute("style", "border-top-right-radius: 0; border-top-left-radius: 0;");
  createBtn1.id = "btnDelete";
  createBtn1.innerText = "삭제";

  createBtn2.setAttribute("type", "button");
  createBtn2.setAttribute("style", "border-top-right-radius: 0; border-top-left-radius: 0;");
  createBtn2.id = "btnUpdate";
  createBtn2.innerText = "수정";

  createDivS1.append(createDivT1);
  createDivS1.append(createDivT2);

  createDivS3.append(createBtn2);
  createDivS3.append(createBtn1);


  createDiv1.append(createDivS1);
  createDiv1.append(createDivS2);
  createDiv1.append(createDivS3);


  createList.append(createDiv1);

  guestCommentWrapper.append(createList);


  // 리스트가 만들어질때마다 해당 리스트의 삭제버튼에 onclick 붙이기
  btnEventSet();
  // commentRender();
};



function btnEventSet() {


  let eventTarget1 = document.getElementsByClassName("delBtn");
  for (i = 0; i < eventTarget1.length; i++) {
    eventTarget1[i].setAttribute("onclick", "deleteList(this)");
  };

  let eventTarget2 = document.getElementsByClassName("updateBtn");
  for (i = 0; i < eventTarget2.length; i++) {
    eventTarget2[i].setAttribute("onclick", "updateComment(this)");
  };
};


// 비동기로 동작해야하며, 해당 함수 호출 시 DB 내용이 업데이트되어야함.
function deleteList(e) {

  // list 배열을 받아와서 지워야할 리스트의 인덱스를 파악해야함.
  // console.log(guestComment);

  console.log("deleteList 실행");


  // 이렇게 하는것이 맞는건가 ^^..
  let parent = e.parentNode.parentNode;
  parent.parentNode.removeChild(parent);

  // 서버와 통신해야함.
};


function updateComment(e) {

  // 기존에 입력되어있던 값 가져와서 임시 변수에 저장.
  // 변경값을 입력받을 엘리먼트 생성
  // fetch PUT 메서드로 기존 테이블의 값 변경하고 비동기 통신하여 리렌더링

  // 현재 선택요소의 부모요소의 (이전)형제요소의 첫번째 자식노드를 comment에 저장. 타입유의
  let comment = e.parentNode.previousElementSibling.firstChild



  //입력폼만들어주기
  // 완료 버튼 만들어주기
  // 수정 취소 버튼 만들기 => 실행 시 이전 Comment값을 re렌더링해줘야함.




}