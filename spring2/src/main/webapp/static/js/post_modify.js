/**
 * /post/modify.jsp에 포함. 
 */

 document.addEventListener('DOMContentLoaded', ()=> {
    const modifyForm = document.querySelector('form#modifyForm');
    const inputId = document.querySelector('input#id');
    const inputTitle = document.querySelector('input#title');
    const textConent = document.querySelector('textarea#content');
    const btnDelete = document.querySelector('button#btnDelete');
    const btnUpdate = document.querySelector('button#btnUpdate');

    //삭제버튼 클릭리스너
    btnDelete.addEventListener('click', ()=>{
        const result =  confirm('정말 삭제할까요');
        if(result) {
            //get방식의 delete요청을 서버로 보내겠다.
            location.href = `delete?id=${inputId.value}`;
        }
        
    });
    
    //업데이트버튼 클릭리스너
    btnUpdate.addEventListener('click', ()=>{
        //제목과 내용이 비어있는지 체크:
    if(inputTitle.value ===''|| textConent.value ===''){
        alert('제목과 내용은 반드시 입력하게요')
        return;
    }
        //업데이트 내용저장 확인하기:
        const result = confirm('변경내용을 저장 할까요?')
        if(result) {
            modifyForm.action='update';//요청주소
            modifyForm.method='post';//요청방식
            modifyForm.submit();//폼양식 데이터 서버로 요청 보냄.
        }    
    });
 });