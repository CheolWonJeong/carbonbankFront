//전화번호 포멧으로 생성
function formatPhoneNumber(input) {
	let numbers = input.value.replace(/\D/g, ''); // 숫자만 남김
	let formatted = "";

	// 11자리까지만 허용
	numbers = numbers.slice(0, 11);

	if (numbers.length < 4) {
		formatted = numbers;
	} else if (numbers.length < 8) {
		formatted = numbers.slice(0, 3) + "-" + numbers.slice(3);
	} else {
		formatted = numbers.slice(0, 3) + "-" + numbers.slice(3, 7) + "-" + numbers.slice(7, 11);
	}

	input.value = formatted;
}	

//사업자번호 포멧으로 생성
function formatBisNumber(input) {
	let numbers = input.value.replace(/\D/g, ''); // 숫자만 남김
	let formatted = "";

	// 10자리까지만 허용
	numbers = numbers.slice(0, 10);

	if (numbers.length < 3) {
		formatted = numbers;
	} else if (numbers.length >= 3 && numbers.length <= 5) {
	    formatted = numbers.slice(0,3) + '-' + numbers.slice(3);
	} else if (numbers.length > 5) {
	    formatted = numbers.slice(0,3) + '-' + numbers.slice(3,5) + '-' + numbers.slice(5);
	}
	
	input.value = formatted;
}	

function checkExtension(fileName, fileSize){

	var regex = new RegExp("(.*?)\.(jpg|jpeg|png|bmp)$");
	var maxSize = 5242880; //5MB


	if(fileSize >= maxSize){
	    alert("파일 사이즈 초과");
	    return false;
	}
	if(!regex.test(fileName)){
	    alert("해당 종류의 파일은 업로드할 수 없습니다.");
	    return false;
	}
	return true;

}

