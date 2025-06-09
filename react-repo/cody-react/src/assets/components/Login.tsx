import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
    const [username, setUsername] = useState("");
    const navigate = useNavigate();

    const handleLogin = (e: React.FormEvent) => {
        e.preventDefault();
        if (username.trim() !== "") {
            // puoi salvare qualcosa in localStorage se vuoi
            navigate("/home"); // vai alla pagina delle forme
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Inserisci nome"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <button type="submit">Entra</button>
            </form>
        </div>
    );
}

export default Login;
