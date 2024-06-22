window.addEventListener("load",function(){
    //al cargar la página buscamos y obtenemos el formulario donde se cargarán los datos del odontólogo a registrar
    const formulario = this.document.querySelector("#form");

    //ante un submit del formulario se ejecuta la siguiente función
    formulario.addEventListener("submit", function(event){
        //creamos un json que tendrá los datos del nuevo odontólogo
        const data = {
            nombre: document.querySelector("#nombre").value,
            apellido: document.querySelector("#apellido").value,
            matricula: document.querySelector("#matricula").value
        };

        //mediante fetch invocamos la api con el método post que registrará los odontólogos en la base de datos.

        const url = "http://localhost:8081/odontologos/registrar";
        const settings={
            method:"POST",
            headers:{
                "Content-Type":"application/json",
            },
            body: JSON.stringify(data)
        }

        fetch(url,settings)
        .then(response => response.json())
        .then(data =>{
            //si no hay ningún error mostramos un mensaje que indica que el odontólogo se registró correctamente.
            let alertaExito =
            `<span>"El odontólogo ha sido registrado correctamente en la base de datos"</span>`

            document.querySelector(".alerta").innerHTML = alertaExito;
            

            resetearForm();
        })

        .catch(error =>{
            //si hay un error mostramos un mensaje que indica que el odontólogo no pudo registrarse.
            let alertaFracaso =
            `<span class ="alerta">"El odontólogo NO ha podido registrarse en la base de datos. Intente nuevamente"</span>`
            
            document.querySelector(".alerta").innerHTML = alertaFracaso;
            resetearForm();

        })

    })
});

    function resetearForm(){
    document.querySelector("#nombre").value = "";
    document.querySelector("#apellido").value = "";
    document.querySelector("#matricula").value = "";
    }

