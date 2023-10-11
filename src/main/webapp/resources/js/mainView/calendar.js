$(document).ready(function () {
  makeCalendar();
});


let nowDate = new Date();
const todayDate = new Date();

//  "<" 클릭시 다음달 view
function prevCalendar() {
  nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() - 1, nowDate.getDate());
  makeCalendar(); //달력 cell 만들어 출력 
}

//  ">" 클릭시 다음달 view
function nextCalendar() {
  nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, nowDate.getDate());
  makeCalendar();
}


//  달력 출력
function makeCalendar() {

  let doMonth = new Date(nowDate.getFullYear(), nowDate.getMonth(), 1);
  let lastDate = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, 0);

  const tbCalendar = document.getElementById("calendar");
  const tbCalendarYM = document.getElementById("tbCalendarYM");
  tbCalendarYM.innerHTML = nowDate.getFullYear() + "년 " + (nowDate.getMonth() + 1) + "월";

  while (tbCalendar.rows.length > 2) {
    tbCalendar.deleteRow(tbCalendar.rows.length - 1);
  }
  let row = null;
  row = tbCalendar.insertRow();
  let cnt = 0;
  for (i = 0; i < doMonth.getDay(); i++) {
    cell = row.insertCell();
    cnt = cnt + 1;
  }

  let cleanDate = [
    { date: '2023-08-10' },
    { date: '2023-08-01' },
    { date: '2023-08-12' }
  ];


  /*달력 출력*/
  for (i = 1; i <= lastDate.getDate(); i++) {
    cell = row.insertCell();
    cell.innerHTML = i;
    cell.classList.add('day');
    cnt = cnt + 1;
    if (cnt % 7 == 1) {

      cell.classList.add('text-danger');
      cell.innerHTML = i;

    }
    if (cnt % 7 == 0) {
      cell.classList.add('text-primary');
      cell.innerHTML = i;

      row = tbCalendar.insertRow();
    }


    for (let j = 0; j < cleanDate.length; j++) {
      let cleanDateString = cleanDate[j].date;
      let cleanDateObject = new Date(cleanDateString);

      if (
        nowDate.getFullYear() == cleanDateObject.getFullYear() &&
        nowDate.getMonth() == cleanDateObject.getMonth() &&
        i == cleanDateObject.getDate()
      ) {
        cell.classList.add('selected');
      }
    };

    /*오늘의 날짜에 표시*/
    if (nowDate.getFullYear() == todayDate.getFullYear()
      && nowDate.getMonth() == todayDate.getMonth()
      && i == todayDate.getDate()) {

      cell.setAttribute('id', 'today');
    }
  }


  // 날짜 선택, 차량 선택시 view 화면 변경
  const selectedDates = document.querySelectorAll(".selected");
  selectedDates.forEach(selectedDate => {
    selectedDate.addEventListener('click', () => {

      const year = nowDate.getFullYear();
      const month = String(nowDate.getMonth() + 1).padStart(2, '0'); // 월을 2자리 문자열로 만듭니다.
      const date = String(selectedDate.innerHTML.padStart(2, '0'));
      const cleanDate = `${year}-${month}-${date}`;


      date.addEventListener('change', function (event) {
        updateMap(); // 날짜가 변경되면 지도 업데이트
      });
      
      let carNumGroup = document.querySelector('#car_num');
      let carNum = null;
    
      carNum.addEventListener('change', function (event) {
        carNum = carNumGroup.options[carNumGroup.selectedIndex].text;
        updateMap(); // 차량 번호가 변경되면 지도 업데이트
      });

      console.log(carNum);
      console.log(cleanDate);
    });
  });

}



// 일자, 차량번호 선택하여 데이터 출력하기
function updateMap() {
  // 선택날짜 출력하기

  // 사용자가 입력한 값을 가져오기

  var viewparams = 'date:' + cleanDate + ';carNum:' + carNum;
  line.getSource().updateParams({ 'viewparams': viewparams });
  point.getSource().updateParams({ 'viewparams': viewparams });
  start_point.getSource().updateParams({ 'viewparams': viewparams });
  end_point.getSource().updateParams({ 'viewparams': viewparams });

  // 중심 좌표 이동
  $.ajax({
    type: "GET",
    url: "/view", // 시작 요청을 보낼 엔드포인트 URL
    data: {
      date: cleanDate,
      carNum: carNum
    },
    dataType: "json",
    success: function (data) {
      var lon = data.x;
      var lat = data.y;

      map.getView().animate({
        center: ol.proj.transform([lon, lat], 'EPSG:4326', 'EPSG:3857'),
        zoom: 15,
        duration: 800
      });
    }

  });

};



// //  달력 생성
// const makeCalendar = () => {

//   // 오늘 날짜 date
//   const today = new Date();

//   //  현재의 년도와 월 받아오기
//   const currentYear = new Date(date).getFullYear();
//   const currentMonth = new Date(date).getMonth() + 1;

//   // 한달전의 마지막 요일
//   const firstDay = new Date(date.setDate(1)).getDay();

//   // 현재 월의 마지막 날 구하기
//   const lastDay = new Date(currentYear, currentMonth, 0).getDate();

//   // 남은 박스만큼 다음날 날짜 표시
//   const limitDay = firstDay + lastDay;
//   const nextDay = Math.ceil(limitDay / 7) * 7;

//   let htmlDummy = '';

//   // 한달전 날짜 표시하기
//   for (let i = 0; i < firstDay; i++) {
//     htmlDummy += `<div class="noColor"></div>`;
//   }

//   // 이번달 날짜 표시하기
//   for (let i = 1; i <= lastDay; i++) {
//     htmlDummy += `<div class="day">${i}</div>`;
//   }

//   // 다음달 날짜 표시하기
//   for (let i = limitDay; i < nextDay; i++) {
//     htmlDummy += `<div class="noColor"></div>`;
//   }

//   document.querySelector(`.dateBoard`).innerHTML = htmlDummy;
//   document.querySelector(`.dateTitle`).innerText = `${currentYear}년 ${currentMonth}월`;

//   //  오늘 날짜 표시하기
//   function todayCheck() {
//     if (currentMonth === today.getMonth() + 1 && currentYear === today.getFullYear()) {
//       for (let date of document.querySelectorAll('.day')) {
//         if (+date.innerHTML === today.getDate()) {
//           date.setAttribute('id', 'today');
//           break
//         }
//       }
//     }
//   }

//   todayCheck();

//   // 청소한 날짜 달력에 표시
//   let cleanDate = [
//     { date: '2023-08-10' },
//     { date: '2023-08-01' },
//     { date: '2023-08-12' }
//   ];

//   for (var i = 0; i < cleanDate.length; i++) {
//     var cleanDateString = cleanDate[i].date;
//     var cleanDateObject = new Date(cleanDateString);
//     for (let day of document.querySelectorAll('.day')) {
//       if (
//         today.getFullYear() == cleanDateObject.getFullYear() &&
//         today.getMonth() + 1 == cleanDateObject.getMonth() + 1 &&
//         day == cleanDateObject.getDate()
//       ) {
//         cell.classList.add('text-bg-primary', 'border', 'border-white');
//       }
//     }
//     console.log(cleanDateObject.getMonth());
//     console.log(today.getMonth() + 1);
//   };


// }

// const date = new Date();
// makeCalendar(date);

// // 이전달 이동
// document.querySelector(`.prevDay`).onclick = () => {
//   makeCalendar(new Date(date.setMonth(date.getMonth() - 1)));
// }

// // 다음달 이동
// document.querySelector(`.nextDay`).onclick = () => {
//   makeCalendar(new Date(date.setMonth(date.getMonth() + 1)));
// }
