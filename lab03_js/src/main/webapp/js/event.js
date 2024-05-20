/**
 * event.html에 포함
 */

const output = document.querySelector('div#output');
const btninput = document.querySelector('button#btninput');




btninput.addEventListener('click', function(e){   
//      console.log(e);//   ->PointerEvent 
        
        const itemInput = document.querySelector('input#itemInput');
        const itemList = document.querySelector('ul#itemList');
        itemList.innerHTML += `<li> ${itemInput.value} </li>`;
        itemInput.value = '';
      
        
});

// TODO: input#itemInput2 요소에 'keydown' 이벤트 리스너를 등록:
// 엔터키가 눌렸을 때, input에 입력된 내용을 ul#itemList2의 리스트 아이템으로 추가.

const itemInput2 = document.querySelector('input#itemInput2');
//console.log(itemInput2);
itemInput2.addEventListener('keydown', function (e){
    const itemList2 = document.querySelector('ul#itemList2');
    itemList2.innerHTML += `<li>${itemInput2.value}</li>`;
    itemInput2.value = '';
});


// TODO: input#username 요소에 'change' 이벤트 리스너를 등록:
// input에 입력된 내용이 바뀔 때마다 div를 입력 내용으로 덮어씀.
const username = document.querySelector('input#username');
//console.log(username);
username.addEventListener('change', function (e) {
    const output = document.querySelector('div#output');
    output.innerHTML = username.value;
    username.value = '';
    output.value='';  
});

// TODO: img#bulb 요소에 'mouseenter' 이벤트 리스너를 등록:
// img의 src를 'images/bulb_on.gif'로 변경.
const bulb = document.querySelector('img#bulb');
bulb.addEventListener('mouseenter', function () {
    <img src="images/bulb_on.gif" />
});

// TODO: img#bulb 요소에 'mouseleave' 이벤트 리스너를 등록:
// img의 src를 'images/bulb_off.gif'로 변경.

bulb = documemt.auerySelector('img#bulb');
bulb.addEventListener('mouseleave', function () {
    <img src="images/bulb_off.gif" />
})

