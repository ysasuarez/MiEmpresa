// Realizar una solicitud GET a la API para obtener los datos de los coches
fetch('http://localhost:8080/coches') // Ajusta la URL a tu punto de conexión en Spring Boot
.then(response => response.json())
.then(coches => {
    const cochesTableBody = document.getElementById('cochesTableBody');

    coches.forEach(coche => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${coche.id}</td>
            <td>${coche.marca}</td>
            <td>${coche.coste}</td>
            <td>${coche.fechaIngreso}</td>
            <td>${coche.vendido ? 'Sí' : 'No'}</td>
            <td>${coche.matricula}</td>
            <td>${coche.precioVenta}</td>
        `;

        cochesTableBody.appendChild(row);
    });
})
.catch(error => {
    console.error('Error al obtener datos de coches:', error);
});