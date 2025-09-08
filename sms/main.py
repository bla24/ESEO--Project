# Exemple de script principal pour la gestion des SMS

def send_sms(number, message):
    print(f"Envoi du message à {number}: {message}")

if __name__ == "__main__":
    send_sms("+33612345678", "Bonjour ! Ceci est un SMS test.")