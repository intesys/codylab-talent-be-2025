import { useEffect, useState } from "react";
function Nomi_Forme() {
    const [forma, setforma] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/forme")
            .then(response => response.json())
            .then(data => setforma(data));
    }, []);



    return(
        <div className="nomi-forme">
            <h1>TIPO:</h1>
            <ul>
                {forma.map(forma => (
                    <li>{forma["id"]} {forma["tipo"]}</li>
                ))}
            </ul>
        </div>
    )
}

export default Nomi_Forme;