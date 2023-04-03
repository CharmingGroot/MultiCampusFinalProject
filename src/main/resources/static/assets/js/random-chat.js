const chatPartners = ["DB자료 활용하기"]; // 채팅 상대 목록

const randomChatPartners = shuffleArray(chatPartners); // 무작위 상대 목록 만들기

// 셔플 함수
function shuffleArray(array) {
  const shuffledArray = [...array];
  for (let i = shuffledArray.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [shuffledArray[i], shuffledArray[j]] = [shuffledArray[j], shuffledArray[i]];
  }
  return shuffledArray;
}

// 상대 목록 출력
console.log("산책 메이트를 추천합니다"); 
randomChatPartners.forEach(partner => {
  console.log(partner);
});
