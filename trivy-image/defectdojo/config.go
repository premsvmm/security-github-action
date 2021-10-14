package defectdojo

type Config struct {
	Accesstoken string
}

var conf *Config

func SetConfig(c Config) {
	conf = &c
}

func GetConfig() Config {
	if conf == nil {
		panic("asana configuration is not done yet")
	}
	return *conf
}
