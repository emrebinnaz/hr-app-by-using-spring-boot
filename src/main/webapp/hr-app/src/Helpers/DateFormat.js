
export const formatDate = (string) => {

    const options = {year: 'numeric', month: 'long', day: 'numeric'};
    return new Date(string).toLocaleDateString([],options);
}

export const getCurrentDate = () => {
        return new Date().toISOString().split('T')[0];
}