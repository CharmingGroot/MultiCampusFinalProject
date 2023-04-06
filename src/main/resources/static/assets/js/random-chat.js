// const chatPartners = ["db목록"]; // 채팅 상대 목록

// const randomChatPartners = shuffleArray(chatPartners); // 무작위 상대 목록 만들기

// // 셔플 함수
// function shuffleArray(array) {
//   const shuffledArray = [...array];
//   for (let i = shuffledArray.length - 1; i > 0; i--) {
//     const j = Math.floor(Math.random() * (i + 1));
//     [shuffledArray[i], shuffledArray[j]] = [shuffledArray[j], shuffledArray[i]];
//   }
//   return shuffledArray;
// }

// // 상대 목록 출력
// console.log("산책 메이트를 추천합니다"); 
// randomChatPartners.forEach(partner => {
//   console.log(partner);
// });

async function recommendFriend() {
    try {
        // MySQL 연결
        const conn = await mysql.createConnection(config);

        // 랜덤하게 사용자 ID 선택
        let [rows, fields] = await conn.execute("SELECT user_id FROM user ORDER BY RAND() LIMIT 1");
        let userId = rows[0].user_id;

        // 선택한 사용자의 친구 중 랜덤하게 추천
        [rows, fields] = await conn.execute("SELECT friend_id FROM friends WHERE user_id = ? ORDER BY RAND() LIMIT 1", [userId]);
        let friendId = rows[0].friend_id;

        // 추천된 친구 정보 출력
        [rows, fields] = await conn.execute("SELECT * FROM user WHERE user_id = ?", [friendId]);
        let friend = rows[0];
        console.log("추천된 친구: " + friend.name);

    } catch (error) {
        console.error(error);
    }
}

recommendFriend();