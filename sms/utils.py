# Fonctions utilitaires pour le projet SMS

def format_number(number):
    # Formatage simple pour un numéro français
    if not number.startswith("+33"):
        return "+33" + number.lstrip("0")
    return number