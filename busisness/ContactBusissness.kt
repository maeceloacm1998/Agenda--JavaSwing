package busisness

import Repository.ContactRepository
import entity.ContactEntity
import java.lang.Exception

class ContactBusissness {
    private fun validate(name: String, phone: String) {
        if (name == "") {
            return throw Exception("Nome é obrigatorio.")
        }

        if (phone == "") {
            return throw  Exception("Telefone e obrigatorio")
        }
    }

    private fun validateDelete(name: String, phone: String){
        if(name == "" || phone == ""){
            return throw  Exception("E necessario selecionar um contato antes de remover.")
        }
    }

    fun save(name: String, phone: String) {
        validate(name, phone)

        val contact = ContactEntity(name,phone)
        ContactRepository.save(contact)
    }

    fun delete(name: String, phone: String){
        validateDelete(name, phone);

        val contact = ContactEntity(name,phone)
        ContactRepository.delete(contact)
    }

    fun getContactCountDescription():String{
        val list = getList();

        return when {
            list.isEmpty() -> "0 contatos"
            list.size == 1 -> "1 contatos"
            else -> "${list.size} contatos"
        }
    }

    fun getList(): List<ContactEntity>{
        return ContactRepository.getList()
    }
}