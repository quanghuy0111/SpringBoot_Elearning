
//Hàm đăng nhập
function login() {
    // B1. LẤY THÔNG TIN FORM ĐĂNG NHẬP
    let email = document.getElementById("email").value;
    let pass = document.getElementById("password").value;

    // B2. GỌI API ĐĂNG NHẬP
    let userLogin = {
        "email": email,
        "password": pass
    };

    axios({
        url: 'http://localhost:8080/api/admin/auth/login',
        method: 'POST',
        data: userLogin
    })
    .then(function(resp){
        // XÓA THÔNG TIN NGƯỜI DÙNG ĐÃ NHẬP TRÊN FORM
        document.getElementById("email").value = "";
        document.getElementById("password").value = "";

        // B3. LƯU TOKEN VÀO MÁY NGƯỜI DÙNG và rediect sang trang user index
        localStorage.setItem('USER_TOKEN', resp.data);
        location.replace("/user-index.html");

    })
    .catch(function(err){
        console.log(err.response)
        swal("Sorry", "Sai email hoặc password", "error");

    })

    
}

