function getProfileImage(){
    
    axios({
        url: 'http://localhost:8080/api/admin/user/current',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            
            let user = resp.data;
            
            idUser = user.id;
            getUserById(idUser);
          
        })
        .catch(function (err) {
            console.log(err);
            
        })

        

}



getProfileImage();

function getUserById(id){
    let profileName = document.getElementById('fullname');
    let profileImage = document.getElementById('profileImage');
    axios({
        url: `http://localhost:8080/api/admin/user/${id}`,
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }

    })
        .then(function (resp) {
            
            let user = resp.data;
            profileImage.src = `http://localhost:8080/profile/${user.avatar}`;
            profileName.innerHTML = user.fullname;
            console.log(user);
        })
        .catch(function (err) {
            console.log(err);
            console.log(user);
            
        })
}