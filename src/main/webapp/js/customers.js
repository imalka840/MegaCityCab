document.addEventListener("DOMContentLoaded", function () {
    const searchInput = document.getElementById("searchInput");
    const customerTable = document.getElementById("customerTable").getElementsByTagName("tbody")[0];

    // Function to fetch customers from the server
    function fetchCustomers() {
        fetch("CustomerServlet?action=fetch")
            .then(response => response.json())
            .then(data => {
                customerTable.innerHTML = "";
                data.forEach(customer => {
                    const row = customerTable.insertRow();
                    row.innerHTML = `
                        <td>${customer.reg_num}</td>
                        <td contenteditable="true" class="editable">${customer.name}</td>
                        <td contenteditable="true" class="editable">${customer.nic}</td>
                        <td contenteditable="true" class="editable">${customer.address}</td>
                        <td contenteditable="true" class="editable">${customer.phone}</td>
                        <td contenteditable="true" class="editable">${customer.email}</td>
                        <td>
                            <button class="update-btn" data-regnum="${customer.reg_num}">Update</button>
                            <button class="delete-btn" data-regnum="${customer.reg_num}">Delete</button>
                        </td>
                    `;
                });
            });
    }

    // Function to handle search
    searchInput.addEventListener("input", function () {
        const filter = searchInput.value.toLowerCase();
        const rows = customerTable.getElementsByTagName("tr");
        
        for (let row of rows) {
            let text = row.innerText.toLowerCase();
            row.style.display = text.includes(filter) ? "" : "none";
        }
    });

    // Event delegation for update and delete buttons
    customerTable.addEventListener("click", function (event) {
        if (event.target.classList.contains("delete-btn")) {
            const regNum = event.target.getAttribute("data-regnum");
            fetch(`CustomerServlet?action=delete&reg_num=${regNum}`, { method: "POST" })
                .then(() => fetchCustomers());
        }
        
        if (event.target.classList.contains("update-btn")) {
            const row = event.target.closest("tr");
            const regNum = event.target.getAttribute("data-regnum");
            const updatedData = {
                reg_num: regNum,
                name: row.cells[1].innerText,
                nic: row.cells[2].innerText,
                address: row.cells[3].innerText,
                phone: row.cells[4].innerText,
                email: row.cells[5].innerText
            };
            fetch("CustomerServlet?action=update", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedData)
            }).then(() => fetchCustomers());
        }
    });
    
    fetchCustomers(); // Initial load
});
