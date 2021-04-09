//Hàm kiểm tra nếu ko có token thì đưa sang về lại trang login
function sendRedirect(){
    if(localStorage.getItem("USER_TOKEN")===null){
        location.replace("/login.html");
    }
}
sendRedirect();