// let textFormBtn = document.getElementById("textFormBtn");

// textFormBtn.addEventListener('click', () => {

//   // let inputComment = this.comment.value;
//   var commentForm = document.commentForm;
//   commentText.push(commentForm.comment.value);
//   // console.log(comment);

// })



// onload될 때 받아온 데이터를 변수에 저장 
// 지금은 더미데이터
let guestName = ["최호근1", "최호근2"];
let commentNumber = ["1", "2"];
let commentText = [];

let guestBookData = {
  commentNumber: commentNumber,
  guestName: guestName,
  commentText: commentText
};


//페이지가 로드되면 실행할 것들
window.onload = () => {
  // fetch로 글번호, 작성자 댓글 가져와서 전역 변수값을 초기화.
  // 글 리스트를 받아 변수에 초기화 
  // 리스트는 유사배열형태로 다룰 수 있도록 클래스를 부여하고
  // 반복문으로 생성하기.

}





function addComment() {


  // 전달받은 form데이터로 배열 초기화하기
  // let commentForm = document.commentForm;
  // commentText.push(commentForm.content.value);








  // ul
  let guestCommentWrapper = document.getElementById('guestCommentWrapper');


  // 최상위 li
  let guestComment = document.createElement("li");
  guestComment.classList.add("guestComment");


  // 2
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
  let guestCommentResult = document.createElement("div");
  formWrapper.appendChild(guestCommentResult).classList.add("guestCommentResult");
  guestCommentResult.innerText = commentForm.content.value;



  // 렌더링
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

  // 리스트가 만들어질때마다 해당 리스트의 삭제버튼에 onclick 붙이기
  btnEventSet();


  // submitData();
};





function btnEventSet() {

  // 클래스 리스트 가져와서 변수에 담기
  // 지울 때 몇번 인덱스에 있는 클래스를 지울지 명시해줘야할듯.

  // console.log("btnEventSet 실행");
  let eventTarget = document.getElementsByClassName("delBtn");
  for (i = 0; i < eventTarget.length; i++) {
    // eventTarget[i].addEventListener('click', (e) => {
    //   // 삭제할 리스트 태그
    //   let guestComment = document.getElementsByClassName("guestComment");
    //   console.log(guestComment[i]);

    //   i--; // 삭제버튼 계속누르면 i때문에  undefined가 출력되는데 어떻게 해결하지
    // });


    eventTarget[i].setAttribute("onclick", "deleteList(this)");
    // console.dir(i);

  };
};


// 비동기로 동작해야하며, 해당 함수 호출 시 DB 내용이 업데이트되어야함.
function deleteList(e) {

  // list 배열을 받아와서 지워야할 리스트의 인덱스를 파악해야함.
  // console.log(guestComment);

  console.log("deleteList 실행");


  // 이렇게 하는것이 맞는건가 ^^..
  let parent = e.parentNode.parentNode.parentNode.parentNode;
  parent.parentNode.removeChild(parent);

  // 서버와 통신해야함.
};















// 비동기로  작성자, form데이터, 작성일시를 서버로 보내기
function submitData() {


  let commentForm = document.commentForm;
  let comment = commentForm.content.value;

  const formData = new FormData();
  formData.append('content', comment);

  fetch('http://localhost:8080/guestbook', {
    method: 'POST', // 또는 'PUT'
    body: JSON.stringify(formData),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log('성공:', data);
    })
    .catch((error) => {
      console.error('실패:', error);
    });

}

// const submitBtn = document.getElementById("textFormBtn");
// submitBtn.addEventListener('click', (e) => {
//   e.preventDefault();

//   const commentForm = document.commentForm;
//   const comment = commentForm.content.value;

//   const formData = new FormData();
//   formData.append('content', comment);

//   fetch('http://localhost:8080/guestbook', {
//     method: 'POST', // 또는 'PUT'
//     body: JSON.stringify(formData),
//   })
//     .then((response) => response.json())
//     .then((data) => {
//       console.log('성공:', data);
//     })
//     .catch((error) => {
//       console.error('실패:', error);
//     });

// });
