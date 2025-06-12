import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (e: { preventDefault: () => void; }) => {
        e.preventDefault();
        if (username.trim() !== "" && password.trim() !== "") {
            try {
                const response = await fetch("http://localhost:8080/api/login", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        id: username,
                        password: password,
                    }),
                    credentials: "include" // Mi serve per l'HTTP-Only cookie, se no me lo dimentico
                });

                if (response.ok) {
                    console.log("Login riuscito");
                    navigate("/home");
                } else if (response.status === 401) {
                    alert("Credenziali non valide");
                } else {
                    alert("Errore durante il login");
                }
            } catch (error) {
                console.error("Errore di rete:", error);
                alert("Errore di rete");
            }
        } else {
            alert("Inserisci nome utente e password");
        }
    };

    return (
        <div className="loginForm">
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Inserisci nome"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Inserisci Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <div className="button">
                    <button type="submit">Entra</button>
                </div>
            </form>
        </div>
    );
}

export default Login;
