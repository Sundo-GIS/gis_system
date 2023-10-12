$(document).ready(function() {
    buildCalendar();
});
        

        var today = new Date();
        var date = new Date();
        function prevCalendar() {
        
         today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
         buildCalendar(); //달력 cell 만들어 출력 
        }
 
        function nextCalendar() {
           
             today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
             buildCalendar();
        }
        function buildCalendar(){
            var doMonth = new Date(today.getFullYear(),today.getMonth(),1);
            
            var lastDate = new Date(today.getFullYear(),today.getMonth()+1,0);
            
            var tbCalendar = document.getElementById("calendar");
            var tbCalendarYM = document.getElementById("tbCalendarYM");
             tbCalendarYM.innerHTML = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월"; 
 
            while (tbCalendar.rows.length > 2) {
                  tbCalendar.deleteRow(tbCalendar.rows.length-1);
             }
             var row = null;
             row = tbCalendar.insertRow();
             var cnt = 0;
             for (i=0; i<doMonth.getDay(); i++) {
                  cell = row.insertCell();
                  cnt = cnt + 1;
             }

             
          //    let jsonData = 
          //    [
          //     {
          //         "carNum": "112하2414",
          //         "dateList": [
          //             "2023-10-01",
          //             "2023-10-02",
          //             "2023-10-03"
          //         ]
          //     },
          //     {
          //         "carNum": "119하6585",
          //         "dateList": [
          //             "2023-10-04",
          //             "2023-10-05",
          //             "2023-10-06"
          //         ]
          //     }
          // ]
            

             let carNumGroup = document.querySelector('#carNumGroup');
             let carNum = null;
             
             carNumGroup.addEventListener('change', function(){
         
                 carNum = carNumGroup.options[carNumGroup.selectedIndex].text;
                 console.log("carNum = " + carNum);
         
             });

             let cleanDate = [ 
              {date : '2023-10-10'},
              {date : '2023-10-01'},
              {date : '2023-10-12'}
            ];

            /*달력 출력*/
             for (i=1; i<=lastDate.getDate(); i++) { 
                  cell = row.insertCell();
                  cell.innerHTML = i;
                  cell.classList.add('day');
                  cnt = cnt + 1;
              if (cnt % 7 == 1) {
                  
                  cell.classList.add('text-danger');
                  cell.innerHTML = i;

            }    
              if (cnt%7 == 0){
                  cell.classList.add('text-primary');
                  cell.innerHTML = i;
                   
                  row = tbCalendar.insertRow();
                  // row.classList.add('mt-1');
                  
              }

              
              for (var j = 0; j < cleanDate.length; j++) {
                var cleanDateString = cleanDate[j].date;
                var cleanDateObject = new Date(cleanDateString);
  
                if (
                  today.getFullYear() == cleanDateObject.getFullYear() &&
                  today.getMonth() == cleanDateObject.getMonth() &&
                  i == cleanDateObject.getDate()
                ) {
                    // cell.classList.add('bg-info', 'text-white', 'border', 'border-white');
                    cell.classList.add('text-bg-primary', 'border', 'border-white');
                }
               };
  

              
              /*오늘의 날짜에 노란색 칠하기*/
              if (today.getFullYear() == date.getFullYear()
                 && today.getMonth() == date.getMonth()
                 && i == date.getDate()) {
                  //달력에 있는 년,달과 내 컴퓨터의 로컬 년,달이 같고, 일이 오늘의 일과 같으면
                // cell.bgColor = "#FAF58C";//셀의 배경색을 노랑으로
                cell.classList.add('text-bg-warning', 'border', 'border-white');
               }
             }



          //    function getCleanDateByCarNum(cleanDate){
          //     let dateString = year + '-' + month + '-' + day;

          //     $.each(cleanDate, function(index, c){
          //          if (c == ) {


          //          } 
      
          //     });
      
          // }

        }