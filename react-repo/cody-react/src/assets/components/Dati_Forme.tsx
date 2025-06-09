import { useEffect, useState } from "react";


function Dati_Forme()
    {
    const [forma, setforma] = useState([]);


    useEffect(() => {
        fetch("http://localhost:8080/api/forme")
            .then(response => response.json())
            .then(data => setforma(data));
    }, []);


        return(
            <div className="dati-forme">
                <h1>DATI:</h1>
                <ul>
                    {forma.map(forma => (
                        <li>{forma["lato1"]} --- {forma["lato2"]}</li>

                    ))}
                </ul>
            </div>
        )
    }

    export default Dati_Forme;