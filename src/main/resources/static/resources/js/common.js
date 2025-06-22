//전화번호 포멧으로 생성
function formatPhoneNumber(input) {
	let numbers = input.value.replace(/\D/g, ''); // 숫자만 남김
	let formatted = "";

	if (numbers.length < 4) {
		formatted = numbers;
	} else if (numbers.length < 8) {
		formatted = numbers.slice(0, 3) + "-" + numbers.slice(3);
	} else {
		formatted = numbers.slice(0, 3) + "-" + numbers.slice(3, 7) + "-" + numbers.slice(7, 11);
	}

	input.value = formatted;
}	
