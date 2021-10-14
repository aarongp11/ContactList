const RETRIEVE_ALL = "http://localhost:8080/contacts"
const POST = "http://localhost:8080/contacts/create"
const DELETE = "http://localhost:8080/contacts/delete/"
const RETRIEVE_ONE = "http://localhost:8080/contacts/retrieve/"
const UPDATE = "http://localhost:8080/contacts/update"

Vue.createApp({
    data() {
        return {
            contacts: [],
            name: "",
            surname: "",
            nif: "",
            currentContact: {}
        }
    },
    methods: {
        async doGet() {
            await axios.get(RETRIEVE_ALL)
                .then((response) => {
                    this.contacts = response.data
                    console.log(response.data)
                })
                .catch((error) => {
                    console.log("Error getting data:" + error)
                })
        },

        async retrieveContact(index) {
            console.log("Contact at index: ", index);
            var url = RETRIEVE_ONE + this.contacts[index].nif;
            var coso = this;
            await axios.get(url)
                .then(function (response) {
                    console.log(response.data);
                    coso.currentContact = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                })
        },

        async createContact(name, surname, nif) {
            var self = this; // Para tener acceso a las funciones declaradas en methods.
            let contact = {
                name: name,
                surname: surname,
                nif: nif
            };
            await axios.post(POST, contact)
                .then(function (response) {
                    console.log(response);
                    self.doGet();
                })
                .catch(function (error) {
                    console.log(error);
                })
        },

        async updateContact() {
            const coso = this;
            await axios.put(UPDATE, this.currentContact)
                .then(function (response) {
                    console.log(response);
                    coso.doGet();
                })
                .catch(function (error) {
                    console.log(error);
                })
        },

        async deleteContact(index) {
            console.log("Removing contact at index:", index);
            var coso = this;
            var url = DELETE + this.contacts[index].nif;
            await axios.delete(url)
                .then(function (response) {
                    console.log(response);
                    coso.doGet();
                })
                .catch(function (error) {
                    console.log(error);
                })
        }
    },
    mounted() {
        this.doGet()
    }
}).mount("#app")