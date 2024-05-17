/**
 * while.html에포함
 */

//document.getElementById('list');   -->id이름만 아규먼트로 가능함.
 const list = document.querySelector('#list');
 const tableBody = document.querySelector('#tableBody');
 let html=''; //<ul></ul>태그의 컨텐드로 작성할 html코드.
 
 let n =1;
 while(n <= 5) {
    html += `<li>아이템 ${n}</li>`;
    n++;
 }
 
 list.innerHTML = html;
 
 
 
 let tableHtml = '';
 let t = 1;
 while(t <= 5) {
    tableHtml += `<tr><td>번호 ${t}</td>
                      <td>제목 ${t}</td>
                  </tr>`;
    t++;
 }
 tableBody.innerHTML = tableHtml;
 
 