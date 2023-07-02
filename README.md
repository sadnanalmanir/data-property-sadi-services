# Data Property SADI Services
The project uses HAS_VALUE data property to extract data values on the 
faceted HYDRA GUI. The goal is to test if a data value attached to the 
HAS_VALUE property modified on the GUI has any impact on the output of 
a service.

## Types of data values as inputs
- xsd:string
- xsd:boolean
- xsd:integer
- xsd:float
- xsd:datetime

## Services

### getGreeting - xsd:string with success
- Input: 
```text
(rdf:type value FirstName)
 and (HAS_VALUE some xsd:string)
```
- Output:
```text
greeted_as some xsd:string with success
```

### getAccommodation - xsd:boolean
- Input:
```text
(rdf:type value Disability)
 and (HAS_VALUE some xsd:boolean)
```
- Output:
```text
need_accommodation some xsd:boolean
```

### getAge - xsd:integer with success
- Input:
```text
(rdf:type value BirthYear)
 and (HAS_VALUE exactly 1 xsd:integer)
```
- Output:
```text
years_old some xsd:integer
```

### getConvertedCurrency - xsd:float with success
- Input:
```text
(rdf:type value AmountInUSD)
 and (HAS_VALUE exactly 1 xsd:float)
```
- Output:
```text
converted_to some xsd:float
```

### getGreetingFail - xsd:string with failure
- Input:
```text
(has_first_name some 
    ((rdf:type value FirstName)
     and (HAS_VALUE some xsd:string)))
 and (rdf:type value Person)
```
- Output:
```text
greeted_as some xsd:string
```
### getFullNameFail with failure
- Input:
```text
(has_first_name some 
    ((rdf:type value FirstName)
     and (HAS_VALUE some xsd:string)))
 and (has_last_name some 
    ((rdf:type value LastName)
     and (HAS_VALUE some xsd:string)))
 and (rdf:type value Person)
```
- Output:
```text
has_full_name some 
    ((rdf:type value FullName)
     and (HAS_VALUE some xsd:string))
```
### computeBMISuccess - xsd:float with success
- Input:
```text
(rdf:type value Measurement)
 and (HAS_VALUE some xsd:float)
```
- Output:
```text
has_BMI some xsd:float
```

### computeBMIFail - xsd:float  with failure
- Input:
```text
(has_height some 
    ((rdf:type value Measurement)
     and (HAS_VALUE some xsd:float)))
 and (has_mass some 
    ((rdf:type value Measurement)
     and (HAS_VALUE some xsd:float)))
 and (rdf:type value Person)
```
- Output:
```text
has_BMI some xsd:float
```


## Deploy on HYDRA and test the services

### hydra-gui-backend settings

#### Load services on when HYDRA-GUI starts 
- Add services in a YAML file e.g. `hydra-gui-backend/WEB-INF/classes/registry/[FILENAME]_services.yaml`
- Load services on HYDRA start-up by adding `FILENAME` above in `hydra-gui-backend/WEB-INF/classes/remote_service_registry_config.properties` such that 
`hydra.registry.initial_contents=FILENAME`

#### Load predefined queries when HYDRA-GUI starts
- Add queries in a JSON file e.g. `hydra-gui-backend/WEB-INF/classes/queries/[FILENAME]_demo.json`
- Load services on HYDRA start-up by adding `FILENAME` above in `hydra-gui-backend/WEB-INF/classes/hydra_gui_backend.properties` such that
  `hydra.gui.backend.init_queries=FILENAME_demo`

### With docker-compose

The project directory contains [`docker-compose.yaml`](https://docs.docker.com/compose/install/) that uses a `Dockerfile` to package the sourcecode with multi-stage build.  

```
cd /path/to/project
docker-compose up --build -d
```

## Queryies on the HYDRA GUI

- Open http://localhost:8080/hydra-gui-backend/front_end/login.html

- credentials: artjom.klein@ipsnp.com, sadi
- Select queries from the menu


## Test inputs from command line using curl

```
cd data-property-sadi-services

```

The following `curl` commands will result in the successful invocation of the services.

> **:warning:** On HYDRA GUI, however, not all services are invoked. It seems that if HAS_VALUE data property
> is not attached to the root node `input` within the method `processInput(Resource input, Resource output)`, 
> the service is not invoked.

### getGreeting 

```shell
curl -H 'Content-Type: text/rdf+n3' -H 'Accept: text/rdf+n3' --data @./ontology-data/sample_input/getGreeting/1.n3 http://localhost:8080/data-property-sadi-services/getGreeting
```

### getAccommodation

```shell
curl -H 'Content-Type: text/rdf+n3' -H 'Accept: text/rdf+n3' --data @./ontology-data/sample_input/getAccommodation/1.n3 http://localhost:8080/data-property-sadi-services/getAccommodation
```

### getAge

```shell
curl -H 'Content-Type: text/rdf+n3' -H 'Accept: text/rdf+n3' --data @./ontology-data/sample_input/getAge/1.n3 http://localhost:8080/data-property-sadi-services/getAge
```

### getConvertedCurrency

```shell
curl -H 'Content-Type: text/rdf+n3' -H 'Accept: text/rdf+n3' --data @./ontology-data/sample_input/getConvertedCurrency/1.n3 http://localhost:8080/data-property-sadi-services/getConvertedCurrency
```

### getGreetingFail

```shell
curl -H 'Content-Type: text/rdf+n3' -H 'Accept: text/rdf+n3' --data @./ontology-data/sample_input/getGreetingFail/1.n3 http://localhost:8080/data-property-sadi-services/getGreetingFail
```

### getFullNameFail

```shell
curl -H 'Content-Type: text/rdf+n3' -H 'Accept: text/rdf+n3' --data @./ontology-data/sample_input/getFullNameFail/1.n3 http://localhost:8080/data-property-sadi-services/getFullNameFail
```

### computeBMIFail

```shell
curl -H 'Content-Type: text/rdf+n3' -H 'Accept: text/rdf+n3' --data @./ontology-data/sample_input/computeBMIFail/1.n3 http://localhost:8080/data-property-sadi-services/computeBMIFail
```


### computeBMISuccess

```shell
curl -H 'Content-Type: text/rdf+n3' -H 'Accept: text/rdf+n3' --data @./ontology-data/sample_input/computeBMISuccess/1.n3 http://localhost:8080/data-property-sadi-services/computeBMISuccess
```
