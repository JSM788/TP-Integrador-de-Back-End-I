window.addEventListener("load",function(){

        //con fetch hacemos una petición a la api de odontólogos, con el método GET. Nos devolverá un JSON con una colección de odontólogos.
        const url = "http://localhost:8081/odontologos/registrar";
        const settings={
            method:"GET"
        };

        fetch(url,settings)

        .then(response => response.json())
        .then(data =>{
            //Recorremos la colección de odontólogos del JSON, por cada odontólogo se genera una fila en la tabla.
                console.log(data);
                let fila = document.querySelector("#filas");
                for (let i = 0; i < data.length; i++) {
                    let contenido = `
                        <tr>
                            <td>${data[i].id}</td>
                            <td>${data[i].nombre}</td>
                            <td>${data[i].apellido}</td>
                        </tr>`;
                        fila.innerHTML += contenido;

                }
        
        })
        window.addEventListener('load', () => {
            const registro = document.querySelector('#new_odontologo');
        
            registro.addEventListener('submit', (e) => {
                e.preventDefault();
        
                const formData = {
                    matricula: document.querySelector('#matricula').value,
                    nombre: document.querySelector('#nombre').value,
                    apellido: document.querySelector('#apellido').value,
                };
        
                const url = "http://localhost:8081/odontologos/registrar";
                const settings = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData)
                };
        
                fetch(url, settings)
                    .then(response => {
                        if (!response.ok) throw new Error('Error en la solicitud al servicio');
                        return response.json();
                        })
                        .then( data => {
                            const id_response = "El ID del odontólogo " + data.nombre + " " + data.apellido + " es " + data.id;
                            alert(id_response);
                            actualizarListado();
                        })
                        .catch(error => {
                            console.error('Error:', error);
                            actualizarListado();
                        });
            });
        });
        
        function actualizarListado() {
            document.querySelector('#matricula').value = "";
            document.querySelector('#nombre').value = "";
            document.querySelector('#apellido').value = "";
        }
    });
