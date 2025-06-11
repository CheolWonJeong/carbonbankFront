//전화번호 포멧으로 생성
function formatPhoneNumber(input) {
    let value = input.value.replace(/[^0-9]/g, ""); // 숫자만 남기기
    let result = "";

    if (value.length < 4) {
        result = value;
    } else if (value.length < 8) {
        result = value.slice(0, 3) + "-" + value.slice(3);
    } else {
        result = value.slice(0, 3) + "-" + value.slice(3, 7) + "-" + value.slice(7, 11);
    }

    input.value = result;
}

//전화번호 효성 검사
function validatePhoneNumber(phone) {
    const regex = /^01[0|1|6|7|8|9]-\d{3,4}-\d{4}$/;
    return regex.test(phone);
}
