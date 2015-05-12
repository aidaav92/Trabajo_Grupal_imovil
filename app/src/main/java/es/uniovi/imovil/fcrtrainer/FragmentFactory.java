/*

Copyright 2014 Profesores y alumnos de la asignatura Informática Móvil de la EPI de Gijón

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */

package es.uniovi.imovil.fcrtrainer;

import es.uniovi.imovil.fcrtrainer.digitalinformation.BinaryExerciseFragment;
import es.uniovi.imovil.fcrtrainer.digitalinformation.BinaryOffsetExerciseFragment;
import es.uniovi.imovil.fcrtrainer.digitalinformation.FloatingPointExerciseFragment;
import es.uniovi.imovil.fcrtrainer.digitalinformation.HexadecimalExerciseFragment;
import es.uniovi.imovil.fcrtrainer.digitalinformation.SignedMagnitudeExerciseFragment;
import es.uniovi.imovil.fcrtrainer.digitalinformation.TwosComplementExerciseFragment;
import es.uniovi.imovil.fcrtrainer.digitalsystems.DigitalCircuitExerciseFragment;
import es.uniovi.imovil.fcrtrainer.digitalsystems.LogicGateExerciseFragment;
import es.uniovi.imovil.fcrtrainer.digitalsystems.LogicOperationExerciseFragment;
import es.uniovi.imovil.fcrtrainer.highscores.HighscoresFragment;
import es.uniovi.imovil.fcrtrainer.networks.CidrExerciseFragment;
import es.uniovi.imovil.fcrtrainer.networks.HostCountExerciseFragment;
import es.uniovi.imovil.fcrtrainer.networks.NetworkAddressExerciseFragment;
import es.uniovi.imovil.fcrtrainer.networks.NetworkLayerExerciseFragment;
import es.uniovi.imovil.fcrtrainer.networks.NetworkMaskExerciseFragment;
import es.uniovi.imovil.fcrtrainer.networks.ProtocolExerciseFragment;
import android.support.v4.app.Fragment;

/**
 * Clase factor�a para crear los diferentes fragmentos de los ejercicios. Cada
 * alumno deber�a insertar la llamada a su fragmento.newInstance() donde le
 * corresponda.
 * 
 */
final public class FragmentFactory {

	/**
	 * Crea el fragmento de un ejercicio a partir de un �ndice. Este �ndice
	 * coincide con el �ndice de la opci�n correspondiente en el Drawer.
	 * 
	 * @param �ndice
	 *            del ejercicio en el Drawer.
	 * @return el fragmento del ejercicio.
	 */
	static Fragment createExercise(int resIndex) {
		switch (resIndex) {
		case R.string.highscores:
			return HighscoresFragment.newInstance();
		case R.string.logic_gate:
			return LogicGateExerciseFragment.newInstance();
		case R.string.digital_circuit:
				return DigitalCircuitExerciseFragment.newInstance();
		case R.string.logic_operation:
			return LogicOperationExerciseFragment.newInstance();
		case R.string.hexadecimal:
			return HexadecimalExerciseFragment.newInstance();
		case R.string.offset_binary:
			return BinaryOffsetExerciseFragment.newInstance();
		case R.string.twoscomplement:
			return TwosComplementExerciseFragment.newInstance();
		case R.string.cidr:
			return CidrExerciseFragment.newInstance();
		case R.string.network_layer:
			return NetworkLayerExerciseFragment.newInstance();
		case R.string.protocol:
			return ProtocolExerciseFragment.newInstance();
		case R.string.binary:
			return BinaryExerciseFragment.newInstance();
		case R.string.host_count:
			return HostCountExerciseFragment.newInstance();
		case R.string.sign_and_magnitude:
			return SignedMagnitudeExerciseFragment.newInstance();
		case R.string.floating_point:
			 return FloatingPointExerciseFragment.newInstance();
		case R.string.network_address:
			return NetworkAddressExerciseFragment.newInstance();
		case R.string.network_mask:
			return NetworkMaskExerciseFragment.newInstance();
		default:
			return BinaryExerciseFragment.newInstance();
		}
	}
}
