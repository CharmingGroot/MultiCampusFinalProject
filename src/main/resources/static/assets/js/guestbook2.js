
// window.onload = () => {
//   localStorage.setItem('jwtToken', response.data.jwtToken);

// }


const commentForm = document.getElementById('commentForm');
console.log(commentForm);
commentForm.addEventListener('submit', async (event) => {
  event.preventDefault();

  let commentForm = document.getElementById("commentForm");


  let comment = document.commentForm.content.value;

  const data = { comment: comment };

  await fetch('http://localhost:8080/guestbook/upload', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log('성공:', data);
    })
    .catch((error) => {
      console.error('실패:', error);
    });



});



// $(function () {
//   $('#submit').on("click", function () {

//     var form1 = $("#commentForm").serialize();

//     console.log(form1);
//     $.ajax({
//       type: "post",
//       url: "/gusetbook/upload",
//       data: form1,
//       dataType: 'json',
//       success: function (data) {
//         alert("success");
//         console.log(data);
//       },
//       error: function (request, status, error) {
//         console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);

//       }
//     });
//   });
// });