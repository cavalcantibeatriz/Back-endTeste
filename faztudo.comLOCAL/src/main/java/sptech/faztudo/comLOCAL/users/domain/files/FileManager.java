package sptech.faztudo.comLOCAL.users.domain.files;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sptech.faztudo.comLOCAL.users.UserRole;
import sptech.faztudo.comLOCAL.users.domain.contractor.Contractor;
import sptech.faztudo.comLOCAL.users.domain.users.User;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class FileManager {

    public static void gravarCSV(List<User> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar o arquivo
        try {
            for (int i = 0; i < lista.size(); i++) {

                User user = lista.get(i);
                saida.format("%d;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        user.getId(),user.getName(),user.getLastName(),user.getCpf(),user.getDt_nascimento(),
                        user.getCep(),user.getLogradouro(),user.getState(),user.getCity(),user.getPhone(),
                        user.getEmail(),user.getSenha(),user.getDt_cadastro(),user.getDescricao(),user.getDt_cadastro(),
                        user.getRole());


            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static List<User> ordenarCSV(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        List<User> users = new ArrayList<>(); // Crie uma lista para armazenar os dados lidos do arquivo.

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo e preencher a lista de usuários
        try {
            while (entrada.hasNext()) {

                Integer id = Integer.parseInt(entrada.next());
                String nome = entrada.next();
                String sobrenome = entrada.next();
                String cpf = entrada.next();
                LocalDate dt_nascimento = LocalDate.parse(entrada.next());
                String cep = entrada.next();
                String logradouro = entrada.next();
                String estado = entrada.next();
                String cidade = entrada.next();
                String telefone = entrada.next();
                String email = entrada.next();
                String senha = entrada.next();
                LocalDateTime dt_cadastro = LocalDateTime.parse(entrada.next());
                String descricao = entrada.next();
                byte[] image_profile_base64 = new byte[]{entrada.nextByte()};
                String role = entrada.next();

                User user = new User(id,nome,sobrenome,cpf,dt_nascimento,cep
                ,logradouro,estado,cidade,telefone,email,senha,dt_cadastro,descricao,image_profile_base64, UserRole.valueOf(role));

                users.add(user);
                System.out.println(users);



            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
        }

        if (!deuRuim) {
            // Ordenar a lista de usuários por nome
            users.sort(Comparator.comparing(User::getName));

            // Gravar o arquivo CSV ordenado
            FileManager.gravarCSV(users, "ArquivoCSVOrdenado");
        }

        return users; // Retorna a lista ordenada (ou não) de usuários.
    }

    public static ResponseEntity<User> acharCSV(String nomeArq, String nomeAProcurar) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        List<User> users = new ArrayList<>(); // Crie uma lista para armazenar os dados lidos do arquivo.

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo e preencher a lista de usuários
        try {
            while (entrada.hasNext()) {
                Integer id = Integer.parseInt(entrada.next());
                String nome = entrada.next();
                String sobrenome = entrada.next();
                String cpf = entrada.next();
                LocalDate dt_nascimento = LocalDate.parse(entrada.next());
                String cep = entrada.next();
                String logradouro = entrada.next();
                String estado = entrada.next();
                String cidade = entrada.next();
                String telefone = entrada.next();
                String email = entrada.next();
                String senha = entrada.next();
                LocalDateTime dt_cadastro = LocalDateTime.parse(entrada.next());
                String descricao = entrada.next();
                byte[] image_profile_base64 = new byte[]{entrada.nextByte()};
                String role = entrada.next();

                User user = new User(id,nome,sobrenome,cpf,dt_nascimento,cep
                        ,logradouro,estado,cidade,telefone,email,senha,dt_cadastro,descricao,
                        image_profile_base64, UserRole.valueOf(role));
                users.add(user);
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
        }

        if (!deuRuim) {

            // Implementar pesquisa binária para procurar o nome desejado na lista de usuários
            int left = 0;
            int right = users.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                User midUser = users.get(mid);
                int comparison = nomeAProcurar.compareTo(midUser.getName());

                if (comparison == 0) {
                    // Nome encontrado, retornar o usuário correspondente
                    return ResponseEntity.status(HttpStatus.FOUND).body(midUser);
                } else if (comparison < 0) {
                    // O nome está à esquerda de midUser, ajustar a posição à esquerda
                    right = mid - 1;
                } else {
                    // O nome está à direita de midUser, ajustar a posição à direita
                    left = mid + 1;
                }
            }

            // Nome não encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            System.out.println("Problema ao ler o arquivo CSV.");
        }

        // Nome não encontrado, ou problema ao ler o arquivo, retornar null
        return null;
    }

    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(List<User> lista, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00USUARIO"; //Verificar documento de layout
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Grava os registros de dados (ou registros de corpo)
        for (User a : lista) {
            String corpo = "02";
            corpo += String.format("%7d", a.getId()); //Completar de acordo com documento
            corpo += String.format("%15s", a.getName());
            corpo+= String.format("%20s",a.getLastName());
            corpo+= String.format("%25s",a.getCpf());
            corpo+= String.format("%15s",a.getDt_nascimento());
            corpo+= String.format("%13s",a.getCep());
            corpo+= String.format("%65s",a.getLogradouro());
            corpo+= String.format("%15s",a.getState());
            corpo+= String.format("%34s",a.getCity());
            corpo+= String.format("%20s",a.getPhone());
            corpo+= String.format("%55s",a.getEmail());
            corpo+= String.format("%30s",a.getSenha());
            corpo+= String.format("%30s",a.getDt_cadastro());
            corpo+= String.format("%256s",a.getDescricao());
            corpo+= String.format("%10s",a.getRole());
            //Gravando corpo no arquivo:
            gravaRegistro(corpo, nomeArq);
            // Incrementa o contador de registros de dados gravados
            contaRegDados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);

        gravaRegistro(trailer, nomeArq);
    }

    public static List<User> leArquivoTxt(String nomeArq) {

        BufferedReader entrada = null;
        String registro, tipoRegistro;
        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;

        List<User> listaLida = new ArrayList<>();


        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }


        try {
            registro = entrada.readLine();
            while (registro != null) {

                tipoRegistro = registro.substring(0,2);


                if (tipoRegistro.equals("00")) {

                    System.out.println("É um registro de header");
                    System.out.println("Tipo de Arquivo: " + registro.substring(2,9));
                    System.out.println("Data e Hora: " + registro.substring(9,28));
                    System.out.println("Versão: " + registro.substring(28,30));


                }

                else if (tipoRegistro.equals("01")) {

                    System.out.println("É um registro de trailer");


                }

                else if (tipoRegistro.equals("02")) {

                    System.out.println("É um registro de corpo");

                    String name = registro.substring(03, 12).trim();
                    System.out.println("nome:"+name);

                    String lastName = registro.substring(13, 24).trim();
                    System.out.println("last:"+lastName);

                    String cpf = registro.substring(25, 37).trim();
                    System.out.println("cpf:"+cpf);

                    String dt = registro.substring(37,50).trim();
                    System.out.println("dt:"+dt);

                    LocalDate data = LocalDate.parse(dt);
                    System.out.println("data:"+data);

                    String cep = registro.substring(50, 60).trim();
                    System.out.println("cep:"+cep);

                    String logradouro = registro.substring(60, 75).trim();
                    System.out.println("logradouro:"+logradouro);

                    String state = registro.substring(76,96).trim();
                    System.out.println("state:"+state);

                    String city = registro.substring(96, 116).trim();
                    System.out.println("city:"+city);

                    String phone = registro.substring(116, 129).trim();
                    System.out.println("phone:"+phone);

                    String email = registro.substring(129, 160).trim();
                    System.out.println("email:"+email);

                    String senha = registro.substring(160, 185).trim();
                    System.out.println("senha:"+senha);

                    String descricao = registro.substring(186,220).trim();
                    System.out.println("descrição:"+descricao);

                    byte[] image_profile_base64 = registro.substring(221,300).trim().getBytes();
                    System.out.println("image_profile_base64:"+image_profile_base64);

                    String valor = registro.substring(301,306).trim();
                    System.out.println("valor:"+valor);

                    UserRole role = UserRole.valueOf(valor);
                    System.out.println("role:"+role);

                    contaRegDadosLidos++;

                    LocalDateTime cad = LocalDateTime.now();

                    String encryptedPassword = new BCryptPasswordEncoder().encode(senha);

                    User a = new User(name,lastName,cpf,data,cep,logradouro,state,city,phone,email,encryptedPassword,
                                     cad,descricao,image_profile_base64, role);

                    listaLida.add(a);



                }
                else {
                    System.out.println("Registro inválido");
                }

                registro = entrada.readLine();

            }

            System.out.println("cheguei aq 1");

            entrada.close();

        }

        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

        System.out.println("cheguei aq 2");

        for (User a : listaLida) {
            System.out.println(a);
        }

        System.out.println("cheguei aq 3");

        return listaLida;
    }

}


//
