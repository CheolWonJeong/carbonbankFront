//1.웹에서 로그인이 완료되어 로딩되는 메인 웹소스에...
//- 아래 처럼 javascript구문을 사용하여 호출하여주세요.

// 앱단 메세지 핸들러 호출상태 체크 함수
function waitForCallHandler(callback_func, attempts = 0) {
    if (window.webkit && window.webkit.messageHandlers) {
        callback_func();
    } else if (attempts < 100) { // 100회까지 시도
        setTimeout(function() {
            waitForCallHandler(callback_func, attempts + 1); // 시도 횟수를 증가
        }, 100);
    } else {
        //console.log('waitForCallHandler: 최대 시도 횟수에 도달했습니다.');
    }
}


//메세지 핸들러 호출함수
waitForCallHandler(function() {
    window.webkit.messageHandlers.cordova_iab.postMessage(JSON.stringify({"action": "savelogin","loginid": "사용자ID","passwd": "사용자PW"}));
});

