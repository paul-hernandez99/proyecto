package interfaces;

import foto.Foto;

public interface IComparable <T extends Foto> 
{
	public abstract boolean despuesDe(T t);
}
