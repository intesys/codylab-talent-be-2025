import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = () => {
        if (username.trim() !== "" && password.trim() !== "") {
            navigate("/home");      //Effettuare controllo con HTTP request 200 (OK) 401 (Unauthorized)
        }
    };

    return (
        <div className = "loginForm">
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
