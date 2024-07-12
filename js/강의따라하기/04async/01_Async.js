//강의 ppt 참고.    절대 멀티스레드가 되는게 아니라는거 명심

function longWork(){
    const now=new Date();
    const milliseconds= now.getTime();
    const afterTwoSeconds= milliseconds+2*1000;
    while(new Date().getTime()<afterTwoSeconds){ //2초동안 while문 실행

    }

    console.log('완료');

}
// console.log("hello");
// longwork();
// console.log('world');


function longWork(){
    setTimeout(()=>{
        console.log("완료");
    } , 2000);
}

console.log("hello");
longWork();
console.log('world');

//이벤트 루프 매우 중요 

