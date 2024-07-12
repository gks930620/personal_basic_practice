/**
 * callBack
 * 
 */


// function waitAndRun(){
//     setTimeout(()=>{
//         console.log("끝");
//     }, 2000);
// }
// waitAndRun();

// function waitAndRun2(){   //콜백안에 콜백 넣지말자. 
//     setTimeout(()=>{
//         console.log("1번 콜백 끝");
//         setTimeout(()=>{
//             console.log("2번 콜백 끝");
//             setTimeout(()=>{
//                 console.log("3번 콜백 끝");
//             },2000);
//         },2000);
//     },2000);
// }

/**
 * Promise
 */


// const timeoutPromise= new Promise( (reseolve,reject)=> {  //순서가중요함. 
//     setTimeout(()=>{
//         reseolve('완료');  //어떤 아규먼트를 설정
//     },2000);
// });

// timeoutPromise.then((res) =>{  
//     console.log("then");
//     console.log(res);
// });


const getPromise= (seconds) => new Promise((resolve,reject)=>{
    setTimeout(()=>{
     //   resolve('완료');  //어떤 아규먼트를 설정
        reject('에러캐치');
    },seconds*1000);
});

// getPromise(3).then((res) =>{  
//     console.log("then");
//     console.log(res);
//     return getPromise(5);
// }).then((res) =>{  
//     console.log("second then");
//     console.log(res);
//     return getPromise(1);
// } ).catch((res)=>{console.log(res);} );  //계속 return하고 then하면 이어서할수있음.

//reject는 에러  , finally는 당연히 그냥 실행됨
getPromise(3).then((res) =>{  
    console.log("then");
    console.log(res);
    return getPromise(5);
}).catch((res)=>{console.log(res);} ); 














