document.addEventListener("DOMContentLoaded", function () {
    loadDashboardStats();
});

// ✅ Fetch and display dashboard statistics
function loadDashboardStats() {
    fetch("http://localhost:8080/api/dashboard/stats", { mode: "cors" })
        .then(response => {
            if(!response.ok) throw new Error(`Server Error: ${response.status}`);
            return response.json();
        })
        .then(data => {
            document.getElementById("totalProjects").innerText = data.totalProjects;
            document.getElementById("highPriorityProjects").innerText = data.highPriorityProjects;
            document.getElementById("delayedProjects").innerText = data.delayedProjects;
        })
        .catch(error => console.error("Error loading dashboard stats:", error));
}

// ✅ Fetch and display **ALL** projects when Total Projects is clicked
function loadTotalProjects() {
    fetch("http://localhost:8080/api/projects", { mode: "cors" })
        .then(response => {
            if(!response.ok) throw new Error(`Server Error: ${response.status}`);
            return response.json();
        })
        .then(data => {
            updateProjectsTable(data, "All Projects");
        })
        .catch(error => console.error("Error loading total projects:", error));
}

// ✅ Fetch and display **High Priority** projects when High Priority Projects is clicked
function loadHighPriorityProjects() {
    fetch("http://localhost:8080/api/dashboard/high-priority-projects", { mode: "cors" })
        .then(response => {
            if(!response.ok) throw new Error(`Server Error: ${response.status}`);
            return response.json();
        })
        .then(data => {
            updateProjectsTable(data, "High Priority Projects");
        })
        .catch(error => console.error("Error loading high priority projects:", error));
}

// ✅ Fetch and display **Delayed Projects** when Delayed Projects is clicked
function loadDelayedProjects() {
    fetch("http://localhost:8080/api/dashboard/delayed-projects", { mode: "cors" })
        .then(response => {
            if(!response.ok) throw new Error(`Server Error: ${response.status}`);
            return response.json();
        })
        .then(data => {
            updateProjectsTable(data, "Delayed Projects");
        })
        .catch(error => console.error("Error loading delayed projects:", error));
}

// ✅ Helper function to update the table with projects
function updateProjectsTable(projects, title) {
    let tableBody = document.getElementById("projectsTableBody");
    let container = document.getElementById("projectsContainer");
    let tableTitle = document.getElementById("projectsTableTitle");

    // Update the table title
    tableTitle.innerText = title;

    // Clear previous table data
    tableBody.innerHTML = "";

    // Add new rows
    projects.forEach(project => {
        let row = `
            <tr>
                <td>${project.trpNumber}</td>
                <td>${project.projectCode}</td>
                <td>${project.partName}</td>
                <td>${project.revision}</td>
                <td>${project.priority}</td>
                <td>${project.type}</td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });

    // Show the table
    container.style.display = "block";
}
